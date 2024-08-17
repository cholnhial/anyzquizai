package dev.chol.anyquizai.exception;

public class SavePhotoException extends RuntimeException {
    public SavePhotoException(String path, String message) {
        super("Failed to save image %s: %s".formatted(path, message));
    }
}
