package dev.chol.anyquizai.dto;

import dev.chol.anyquizai.domain.jpa.Question;
import java.util.List;
import java.util.stream.Collectors;

public record QuestionDTO(String question, String correctAnswerLetter, List<AnswerDTO> answers) {

    public Question toQuestion() {
        return Question.builder()
                .correctAnswerLetter(correctAnswerLetter)
                .title(question)
                .answers(answers.stream().map(AnswerDTO::toAnswer).collect(Collectors.toSet()))
                .build();
    }
}
