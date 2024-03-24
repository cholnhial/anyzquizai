package dev.chol.anyquizai.exception;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException(Long id) {
        super("Quiz with %d not found".formatted(id));
    }
}
