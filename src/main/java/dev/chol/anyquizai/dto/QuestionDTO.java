package dev.chol.anyquizai.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDTO {
    private String question;

    private List<AnswerDTO> answers;
}
