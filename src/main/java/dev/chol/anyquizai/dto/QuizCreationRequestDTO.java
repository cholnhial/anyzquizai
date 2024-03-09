package dev.chol.anyquizai.dto;

import dev.chol.anyquizai.domain.enumeration.Difficulty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record QuizCreationRequestDTO (
    @NotEmpty
    String topic,
    @NotEmpty
    Difficulty difficulty,
    @Min(value = 5)
    @Max(value = 30)
    Integer numberOfQuestions,
    @NotNull
    Long categoryId
) {}
