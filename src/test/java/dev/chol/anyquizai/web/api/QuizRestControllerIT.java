package dev.chol.anyquizai.web.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.chol.anyquizai.dto.QuizCreationRequestDTO;
import dev.chol.anyquizai.enumeration.Difficulty;
import dev.chol.anyquizai.service.QuizSearchService;
import dev.chol.anyquizai.service.QuizService;
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
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
            .withPassword("testpass");

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
    void cleanDatabase() throws SQLException {
        flyway.clean();
        flyway.migrate();

        ScriptUtils.executeSqlScript(dataSource.getConnection(),
                new ClassPathResource("db/testdata/data.sql"));
    }

    @BeforeEach
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
                .andExpect(jsonPath("$.totalElements", is(0)))
                .andExpect(jsonPath("$.number", is(0)))  // Page numbers are zero-based
                .andExpect(jsonPath("$.numberOfElements", is(1)));
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
    void  getScoresByQuizId_givenAQuizId_shouldReturnListOfCores() throws Exception {
        mockMvc.perform(get("/api/quiz/%d/score".formatted(TEST_QUIZ_ID))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }



}