package dev.chol.anyquizai.service;

import dev.chol.anyquizai.CategoryNotFound;
import dev.chol.anyquizai.domain.Category;
import dev.chol.anyquizai.domain.Quiz;
import dev.chol.anyquizai.dto.QuizDTO;
import dev.chol.anyquizai.repository.CategoryRepository;
import dev.chol.anyquizai.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final CategoryService categoryService;


    @Transactional
    public Quiz saveQuizForCategory(QuizDTO quizDto, Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return this.quizRepository.save(quizDto.toQuiz(category));
    }
}
