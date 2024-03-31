package dev.chol.anyquizai.dto;

import dev.chol.anyquizai.domain.jpa.Answer;

public record AnswerDTO (String answerLetter, String answerTitle){

    public Answer toAnswer() {
        return Answer.builder()
                .letter(answerLetter)
                .title(answerTitle)
                .build();
    }
}
