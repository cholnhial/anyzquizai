package dev.chol.anyquizai.dto;

import dev.chol.anyquizai.enumeration.Difficulty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record QuizCreationRequestDTO(
        @NotBlank
        String topic,
        @NotNull
        Difficulty difficulty,
        @Min(value = 5)
        @Max(value = 30)
        Integer numberOfQuestions,
        @NotNull
        Long categoryId
) {
}
