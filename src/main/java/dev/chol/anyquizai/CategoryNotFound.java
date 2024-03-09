package dev.chol.anyquizai;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound(Long id) {
        super("Category with %d not found".formatted(id));
    }
}
