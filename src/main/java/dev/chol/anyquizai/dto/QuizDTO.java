package dev.chol.anyquizai.dto;

import dev.chol.anyquizai.domain.jpa.Category;
import dev.chol.anyquizai.domain.jpa.Quiz;
import dev.chol.anyquizai.enumeration.Difficulty;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.stream.Collectors;

public record QuizDTO(Long id, Long categoryId, String title, Integer numberOfQuestions, Difficulty difficulty, List<QuestionDTO> questions, String thumbnailGenerationPrompt) {

    public Quiz toQuiz(Category category) {
        return Quiz.builder()
                .title(title)
                .uniqueCode(RandomStringUtils.randomAlphanumeric(6))
                .category(category)
                .totalQuestions(numberOfQuestions)
                .difficulty(difficulty)
                .questions(questions.stream().map(QuestionDTO::toQuestion).collect(Collectors.toSet()))
                .build();
    }
}
