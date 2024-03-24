package dev.chol.anyquizai.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("Category with %d not found".formatted(id));
    }
}
