package dev.chol.anyquizai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public record ApplicationProperties(String quizPhotosPath,
                                    Integer quizPhotoWidth,
                                    Integer quizPhotoHeight) {
}
