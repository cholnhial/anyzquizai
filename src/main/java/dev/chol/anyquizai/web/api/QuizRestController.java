package dev.chol.anyquizai.web.api;

import dev.chol.anyquizai.dto.QuizCreationRequestDTO;
import dev.chol.anyquizai.dto.QuizDTO;
import dev.chol.anyquizai.service.AIQuizGeneratorService;
import dev.chol.anyquizai.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
public class QuizRestController {

    private final AIQuizGeneratorService quizGeneratorService;
    private final QuizService quizService;

    @PostMapping
    @Operation(summary = "Generate Quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = QuizDTO.class)) }),
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
}
