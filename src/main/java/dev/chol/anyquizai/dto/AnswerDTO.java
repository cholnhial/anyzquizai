package dev.chol.anyquizai.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {
    private String answerLetter;
    private String answerTitle;

    private Boolean isCorrectAnswer;
}
