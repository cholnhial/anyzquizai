package dev.chol.anyquizai.dto;

import dev.chol.anyquizai.enumeration.Difficulty;
import java.time.LocalDateTime;
import java.util.List;

public record QuizDTO(Long id, Long categoryId, String title, Integer numberOfQuestions, Difficulty difficulty,
                      List<QuestionDTO> questions, LocalDateTime created) {

}
