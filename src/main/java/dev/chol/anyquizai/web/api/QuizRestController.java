package dev.chol.anyquizai.web.api;

import dev.chol.anyquizai.domain.jpa.Quiz;
import dev.chol.anyquizai.domain.jpa.Score;
import dev.chol.anyquizai.dto.*;
import dev.chol.anyquizai.service.AIQuizGeneratorService;
import dev.chol.anyquizai.service.QuizSearchService;
import dev.chol.anyquizai.service.QuizService;
import dev.chol.anyquizai.service.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
public class QuizRestController {

    private final AIQuizGeneratorService quizGeneratorService;
    private final QuizService quizService;
    private final QuizSearchService quizSearchService;
    private final ScoreService scoreService;

    /**
     * {@code POST /api/quiz} : Generates a new quiz
     *
     * @param creationRequestDTO a body containing the quiz to generate
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with a body containing the new generated quiz
     * or with status {@code 404 (Not Found} if category couldn't be found
     * or with status {@code 500 (Internal Server Error)} if Generative AI API failed to generate
     * @throws URISyntaxException if the location URI syntax is incorrect
     */
    @PostMapping
    @Operation(summary = "Generate Quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuizAIDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public ResponseEntity<QuizDTO> generateQuiz(@RequestBody @Valid QuizCreationRequestDTO creationRequestDTO) throws URISyntaxException {
        // invoke quiz service to save quiz
        var quizDto = this.quizGeneratorService.generateQuiz(creationRequestDTO.topic(),
                creationRequestDTO.difficulty(), creationRequestDTO.numberOfQuestions());
        var quiz = this.quizService.saveQuizForCategory(quizDto, creationRequestDTO.categoryId());
        return ResponseEntity.created(new URI("/api/quiz/" + quiz.getId())).body(quiz.toDTO());
    }

    /**
     * {@code GET /api/quiz/:id} : Retrieves a quiz
     *
     * @param id the id of the quiz to retrieve
     * @return {@link ResponseEntity} with status {@code 200 (OK)} and with the body of retrieved quiz
     * or with status {@code 404 (Not Found)} if the quiz identified by the id provided couldn't be found
     */
    @Operation(summary = "Get a quiz by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the quiz",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuizAIDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Quiz not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(this.quizService.getQuizById(id).toDTO());
    }

    /**
     * {@code GET /api/quiz} : Search quizzes
     *
     * @param searchOptions search options
     * @return a page of quizzes if any
     */
    @GetMapping
    @Operation(summary = "Search quizzes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))})}
    )
    public ResponseEntity<Page<dev.chol.anyquizai.domain.elasticsearch.Quiz>> getAll(@RequestParam Map<String, String> searchOptions) {

        var searchOptionsBuilder = SearchDTO.builder();
        if (searchOptions.containsKey("title")) {
            searchOptionsBuilder.title(searchOptions.get("title"));
        }

        if (searchOptions.containsKey("categoryId")) {
            searchOptionsBuilder.categoryId(searchOptions.get("categoryId"));
        }
        if (searchOptions.containsKey("difficulty")) {
            searchOptionsBuilder.sortByDifficulty(Sort.Direction.fromString(searchOptions.get("difficulty")));
        }


        if (searchOptions.containsKey("created")) {
            searchOptionsBuilder.sortByCreatedDate(Sort.Direction.fromString(searchOptions.get("created")));
        }

        if (searchOptions.containsKey("page") && searchOptions.containsKey("size")) {
            searchOptionsBuilder.page(Integer.valueOf(searchOptions.get("page")));
            searchOptionsBuilder.size(Integer.valueOf(searchOptions.get("size")));
        }

        return ResponseEntity.ok(quizSearchService.processSearch(searchOptionsBuilder.build()));
    }

    /**
     * {@code GET /api/quiz/:id/photo} : Returns the AI generated thumbnail of the quiz
     *
     * @param id the quiz
     * @return ResponseEntity containing the byte array representing the photo, along with appropriate headers and HTTP status
     * @throws IOException when file cannot be opened or other file system related errors
     */
    @GetMapping("/{id}/photo")
    @Operation(summary = "Get a quiz thumbnail photo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quiz photo if quiz is found",
                    content = {@Content(mediaType = "image/png")}),
            @ApiResponse(responseCode = "404", description = "Quiz not found",
                    content = @Content)})
    public ResponseEntity<byte[]> getQuizPhoto(@PathVariable Long id) throws IOException {
        Quiz quiz = quizService.getQuizById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        byte[] photoBytes = quiz.getPhotoInBytes();
        headers.setContentLength(photoBytes.length);
        return new ResponseEntity<>(photoBytes, headers, HttpStatus.OK);
    }

    /**
     * {@code GET /api/quiz/:id/score} :Retrieved list of quiz scores
     *
     * @param quizId the id of the quiz to retrieve the scores for
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with a body containing list of scores for the quiz
     */
    @Operation(summary = "Get scores for a quiz", description = "Retrieves a list of Score objects ordered by score for a given quiz ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Score.class)))),
            @ApiResponse(responseCode = "404", description = "Quiz not found", content = @Content)
    })
    @GetMapping("/{quizId}/score")
    public ResponseEntity<List<ScoreDTO>> getScoresByQuizId(@PathVariable Long quizId) {
        return ResponseEntity.ok(scoreService.getAllByQuizIdOrderedByScore(quizId).stream().map(Score::toScoreDTO).toList());
    }

    /**
     * {@code POST /api/quiz/score} : Saves a new score for a quiz
     *
     * @param requestDTO the request data containing the quiz ID, user ID, and score
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with a body containing the saved {@link ScoreDTO}
     */
    @PostMapping("/score")
    public ResponseEntity<ScoreDTO> saveNewScore(@RequestBody @Valid QuizScoreSubmissionRequestDTO requestDTO) {
        return ResponseEntity.ok(scoreService.saveNewScore(requestDTO).toScoreDTO());
    }
}