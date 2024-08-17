package dev.chol.anyquizai.web.api;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.chol.anyquizai.dto.QuizCreationRequestDTO;
import dev.chol.anyquizai.enumeration.Difficulty;
import dev.chol.anyquizai.service.QuizSearchService;
import dev.chol.anyquizai.service.QuizService;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
class QuizRestControllerIT {

    @Container
    static MariaDBContainer<?> mariaDB = new MariaDBContainer<>("mariadb:latest")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass")
            .withStartupTimeout(Duration.ofSeconds(120))
            .waitingFor(Wait.forListeningPort());

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Flyway flyway;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuizSearchService quizSearchService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private DataSource dataSource;

    private final Long TEST_QUIZ_ID = 16L;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.password", mariaDB::getPassword);
        registry.add("spring.datasource.url", mariaDB::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDB::getUsername);
    }


    @Container
    static ElasticsearchContainer elasticsearchContainer = new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:7.17.10");

    @DynamicPropertySource
    static void elasticsearchProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.elasticsearch.uris", elasticsearchContainer::getHttpHostAddress);
    }


    @BeforeEach
    void setupDatabase() {
        try (Connection conn = dataSource.getConnection()) {
            flyway.clean();
            flyway.migrate();
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("db/testdata/data.sql"));
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize the database", e);
        }
        buildElasticQuizIndex();
    }

    void buildElasticQuizIndex() {
        this.quizService.getAll().forEach(quiz -> {
            var elasticQuizBuilder = dev.chol.anyquizai.domain.elasticsearch.Quiz.builder();
            elasticQuizBuilder.id(quiz.getId());
            elasticQuizBuilder.title(quiz.getTitle());
            elasticQuizBuilder.categoryId(quiz.getCategory().getId());
            elasticQuizBuilder.difficulty(quiz.getDifficulty().toString());
            elasticQuizBuilder._difficulty(quiz.getDifficulty().getValue());
            elasticQuizBuilder.uniqueCode(quiz.getUniqueCode());
            elasticQuizBuilder.questions(quiz.getTotalQuestions());
            elasticQuizBuilder.created(quiz.getCreated());
            elasticQuizBuilder.created(quiz.getCreated());
            quizSearchService.createQuizIndex(elasticQuizBuilder.build());
        });
    }

    @Test
    void getQuizById_ShouldReturnQuizWithQuestionsAndAnswers() throws Exception {
        Long quizId = TEST_QUIZ_ID;

        mockMvc.perform(get("/api/quiz/{id}", quizId))
            .andExpect(status().isOk())
            // Validate the quiz structure
            .andExpect(jsonPath("$.id", is(quizId.intValue())))
            .andExpect(jsonPath("$.categoryId", is(notNullValue())))
            .andExpect(jsonPath("$.title", is(notNullValue())))
            .andExpect(jsonPath("$.numberOfQuestions", is(notNullValue())))
            .andExpect(jsonPath("$.difficulty", is(notNullValue())))
            .andExpect(jsonPath("$.created", is(notNullValue())))
            // Validate the questions array is present
            .andExpect(jsonPath("$.questions", hasSize(10)))
            // Validate structure of first question
            .andExpect(jsonPath("$.questions[0].question", is(notNullValue())))
            .andExpect(jsonPath("$.questions[0].correctAnswerLetter", is(notNullValue())))
            .andExpect(jsonPath("$.questions[0].answers", hasSize(4)))
            // Validate structure of the first answer of the first question
            .andExpect(jsonPath("$.questions[0].answers[0].answerLetter", is(notNullValue())))
            .andExpect(jsonPath("$.questions[0].answers[0].answerTitle", is(notNullValue())));
    }


    @Test
    void getAll_returnsQuizzes() throws Exception {
        mockMvc.perform(get("/api/quiz"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", not(empty())))
                .andExpect(jsonPath("$.numberOfElements", greaterThan(0)));
    }

    @Test
    void getAll_withParameters_returnsFilteredQuizzes() throws Exception {
        mockMvc.perform(get("/api/quiz?title=Kenya%20Quiz&difficulty=EASY&page=1&size=9"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", notNullValue()))
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.number", is(0)))  // Page numbers are zero-based
                .andExpect(jsonPath("$.numberOfElements", is(1)));
    }


    @Test
    void getAll_givenSortOrderOfCreatedDesc_shouldHaveQuizzesInRightOrder() throws Exception {
        mockMvc.perform(get("/api/quiz?page=1&size=100&sort_created=DESC"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content", notNullValue()))
            .andExpect(jsonPath("$.content[0].title", is("Hard Rap Music Quiz")));
    }

    @Test
    void getAll_givenDifficultyOfEasy_shouldOnlyReturnEasyQuizzes() throws Exception {
        mockMvc.perform(get("/api/quiz?page=1&size=100&difficulty=EASY"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content", notNullValue()))
            .andExpect(jsonPath("$.content[*].difficulty", everyItem(is("EASY"))));
    }

    @Test
    void getAll_givenDifficultyOfMedium_shouldOnlyReturnMediumQuizzes() throws Exception {
        mockMvc.perform(get("/api/quiz?page=1&size=100&difficulty=MEDIUM"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content", notNullValue()))
            .andExpect(jsonPath("$.content[*].difficulty", everyItem(is("MEDIUM"))));
    }

    @Test
    void getAll_givenSortingDifficultyDESC_shouldExpectFirstToBeHardAndLastToBeEasy()
        throws Exception {
        mockMvc.perform(get("/api/quiz?page=0&size=100&sort=difficulty=DESC"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content", notNullValue()))
            .andExpect(jsonPath("$.content[0].difficulty", is("HARD")))
            .andExpect(jsonPath("$.content[-1].difficulty", is("EASY")));
    }

    @Test
    void getAll_givenDifficultyOfHard_shouldOnlyReturnHardQuizzes() throws Exception {
        mockMvc.perform(get("/api/quiz?page=1&size=100&difficulty=HARD"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content", notNullValue()))
            .andExpect(jsonPath("$.content[*].difficulty", everyItem(is("HARD"))));
    }

    @Test
    void getAll_givenNumberOfQuestionsOfFive_ShouldOnlyReturnQuizzesWithFiveQuestions() throws Exception {
        mockMvc.perform(get("/api/quiz?page=1&size=100&questions=5"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content", notNullValue()))
            .andExpect(jsonPath("$.content[*].numberOfQuestions", everyItem(is(5))));
    }

    @Test
    void getAll_givenCategoryOfOther_shouldOnlyReturnQuizzesWithCategoryOther() throws Exception {
        mockMvc.perform(get("/api/quiz?page=1&size=100&categoryId=18"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content", notNullValue()))
            .andExpect(jsonPath("$.content[*].categoryId", everyItem(is(18))));
    }

    @Test
    void generateQuiz_givenValidQuizRequest_ShouldGenerateQuizWithPhoto() throws Exception {

        QuizCreationRequestDTO requestDTO = new QuizCreationRequestDTO(
                "Java Programming",
                Difficulty.MEDIUM,
                10,
                18L
        );

        // Act & Assert
        MvcResult generateQuizResult = mockMvc.perform(post("/api/quiz")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/api/quiz/")))
                .andExpect(jsonPath("$.categoryId", is(18)))
                .andExpect(jsonPath("$.difficulty", is("MEDIUM")))
                .andExpect(jsonPath("$.numberOfQuestions", is(10)))
                .andExpect(status().isCreated())
                .andReturn();

        String responseContent = generateQuizResult.getResponse().getContentAsString();
        JsonNode root = objectMapper.readTree(responseContent);
        Long quizId = root.path("id").asLong();

        // Check photo
        mockMvc.perform(get("/api/quiz/%d/photo".formatted(quizId))
                        .accept(MediaType.IMAGE_PNG))
                .andExpect(status().isOk()) // Assert that the response status is 200
                .andExpect(content().contentType(MediaType.IMAGE_PNG)) // Assert content type
                .andExpect(result -> {
                    byte[] responseBytes = result.getResponse().getContentAsByteArray();
                    assertTrue(responseBytes.length > 0, "Response byte array should not be empty");
                });
    }

    @Test
    void generateQuiz_givenEmptyQuizTopic_shouldExpectBadRequestStatus400() throws Exception {

        String json = """
                {
                  "topic": "",
                  "difficulty": "EASY",
                  "numberOfQuestions": 3,
                  "categoryId": 18
                }
                """;

        // Act & Assert
        mockMvc.perform(post("/api/quiz")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void  getScoresByQuizId_givenAQuizId_shouldReturnListOfScores() throws Exception {
        mockMvc.perform(get("/api/quiz/%d/score".formatted(TEST_QUIZ_ID))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    void saveNewScore_givenANewScore_shouldSaveItToListOfScores() throws Exception {
        // Arrange
        String json = """
            {
                "quizId": 16,
                "nickname": "obama",
                "totalCorrect": 9,
                "countryCode": "US"
            }
            """;
        // Act & Assert
        mockMvc.perform(post("/api/quiz/score")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/quiz/%d/score".formatted(TEST_QUIZ_ID))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].nickname").value(hasItem("obama")));
    }



}