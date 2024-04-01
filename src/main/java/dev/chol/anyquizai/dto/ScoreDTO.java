package dev.chol.anyquizai.dto;

public record ScoreDTO(String nickname, Integer totalCorrect, Integer totalQuestions, Float score, String countryCode) {
}
