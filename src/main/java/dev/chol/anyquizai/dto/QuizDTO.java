package dev.chol.anyquizai.dto;

import dev.chol.anyquizai.domain.enumeration.Difficulty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizDTO {
    private String title;
    private Integer numberOfQuestions;
    private Difficulty difficulty;
    private List<QuestionDTO> questions;
}
