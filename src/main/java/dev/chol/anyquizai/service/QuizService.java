package dev.chol.anyquizai.service;

import dev.chol.anyquizai.config.ApplicationProperties;
import dev.chol.anyquizai.domain.Category;
import dev.chol.anyquizai.domain.Quiz;
import dev.chol.anyquizai.dto.QuizDTO;
import dev.chol.anyquizai.exception.QuizNotFoundException;
import dev.chol.anyquizai.exception.SavePhotoException;
import dev.chol.anyquizai.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final CategoryService categoryService;
    private final AIImageGeneratorService aiImageGeneratorService;
    private final ApplicationProperties applicationProperties;

    /**
     * Save a generated quiz for a given category and generate its photo and save it to disk
     *
     * @param quizDto the newly generated quiz to save
     * @param categoryId the category to save the quiz under
     * @return an entity persisted of the new quiz
     */
    @Transactional
    public Quiz saveQuizForCategory(QuizDTO quizDto, Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        Quiz quiz = quizRepository.save(quizDto.toQuiz(category));
        byte[] photoBytes = aiImageGeneratorService.generateImage(quizDto.thumbnailGenerationPrompt(), applicationProperties.quizPhotoWidth(),
                applicationProperties.quizPhotoHeight());
        String quizPhotoPath = "%s/%d.png".formatted(applicationProperties.quizPhotosPath(), quiz.getId());
        savePhoto(quizPhotoPath, photoBytes);
        quiz.setPhotoPath(quizPhotoPath);
        return quiz;
    }

    /**
     * Retrieves a quiz by specified id
     *
     * @param id the id of the quiz to retrieve
     * @return a quiz that is found or else RuntimeException is thrown
     */
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(id));
    }


    /**
     * Saves an already generated quiz photo to disk
     * @param quizPhotoPath the path to save the photo  to
     * @param photoBytes the bytes for the photo (retrieve from AI or otherwise)
     */
    private void savePhoto(String quizPhotoPath, byte[] photoBytes) {
        try {
            Path path = Paths.get(quizPhotoPath);
            Files.write(path, photoBytes);
        } catch (IOException e) {
            throw new SavePhotoException(quizPhotoPath, e.getMessage());
        }
    }


}
