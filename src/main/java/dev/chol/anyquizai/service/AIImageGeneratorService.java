package dev.chol.anyquizai.service;

public interface AIImageGeneratorService {
    byte[] generateImage(String prompt, int width, int height);
}
