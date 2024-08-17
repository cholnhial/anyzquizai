package dev.chol.anyquizai.dto;

import dev.chol.anyquizai.domain.jpa.Score;
import jakarta.validation.constraints.NotEmpty;

public record QuizScoreSubmissionRequestDTO(@NotEmpty Long quizId, @NotEmpty String nickname,
                                            @NotEmpty Integer totalCorrect, @NotEmpty String countryCode) {

    public Score toScore(long quizTotalQuestions) {
        return Score.builder()
                .nickName(nickname)
                .totalCorrect(totalCorrect)
                .countryCode(countryCode)
                .score(((float) totalCorrect / (float) quizTotalQuestions)).build();
    }
}
