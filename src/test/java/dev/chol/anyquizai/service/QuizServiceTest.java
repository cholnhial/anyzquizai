package dev.chol.anyquizai.service;

import dev.chol.anyquizai.config.ApplicationProperties;
import dev.chol.anyquizai.domain.jpa.Category;
import dev.chol.anyquizai.domain.jpa.Quiz;
import dev.chol.anyquizai.dto.AnswerDTO;
import dev.chol.anyquizai.dto.QuestionDTO;
import dev.chol.anyquizai.dto.QuizAIDTO;
import dev.chol.anyquizai.dto.QuizDTO;
import dev.chol.anyquizai.enumeration.Difficulty;
import dev.chol.anyquizai.exception.QuizNotFoundException;
import dev.chol.anyquizai.repository.jpa.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;
    @Mock
    private CategoryService categoryService;
    @Mock
    private AIImageGeneratorService aiImageGeneratorService;
    @Mock
    private QuizSearchService quizSearchService;
    @Mock
    private ApplicationProperties applicationProperties;


    @InjectMocks
    @Spy
    private QuizService quizService;


    final QuizAIDTO quizAIDTO = new QuizAIDTO(1L, 1L,
            "Hello", 5, Difficulty.EASY,
            List.of(new QuestionDTO("A", "A",
                    List.of(new AnswerDTO("A", "Chicken"),
                            new AnswerDTO("B", "Chicken"),
                            new AnswerDTO("C", "Chicken"),
                            new AnswerDTO("D", "Chicken")))),
            "asd");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveQuizForCategory() {
        // Arrange

        Long categoryId = 1L;
        Category category = new Category();
        Quiz quiz = quizAIDTO.toQuiz(category);
        quiz.setId(1L);

        when(categoryService.getCategoryById(categoryId)).thenReturn(category);
        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);
        when(aiImageGeneratorService.generateImage(anyString(), anyInt(), anyInt())).thenReturn(new byte[0]);
        when(applicationProperties.quizPhotosPath()).thenReturn("/path/to/photos");
        when(applicationProperties.quizPhotoWidth()).thenReturn(100);
        when(applicationProperties.quizPhotoHeight()).thenReturn(100);
        // Mock out the savePhoto method
        doNothing().when(quizService).savePhoto(anyString(), any(byte[].class));
        // Act
        Quiz result = quizService.saveQuizForCategory(quizAIDTO, categoryId);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(quizRepository).save(any(Quiz.class));
        verify(aiImageGeneratorService).generateImage(anyString(), anyInt(), anyInt());
        verify(quizSearchService).createQuizIndex(any());

        // Verify that savePhoto was called
        verify(quizService).savePhoto(anyString(), any(byte[].class));
    }


    @Test
    void getQuizById_ExistingQuiz_ReturnsQuiz() {
        // Arrange
        Long quizId = 1L;
        Category category = new Category();
        Quiz expectedQuiz = quizAIDTO.toQuiz(category);
        expectedQuiz.setId(quizId);
        when(quizRepository.findById(quizId)).thenReturn(Optional.of(expectedQuiz));

        // Act
        Quiz result = quizService.getQuizById(quizId);

        // Assert
        assertNotNull(result);
        assertEquals(quizId, result.getId());
        verify(quizRepository).findById(quizId);
    }

    @Test
    void getQuizById_NonExistingQuiz_ThrowsQuizNotFoundException() {
        // Arrange
        Long quizId = 1L;
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(QuizNotFoundException.class, () -> quizService.getQuizById(quizId));
        verify(quizRepository).findById(quizId);
    }

    @Test
    void getQuizByIdAsDTO_ExistingQuiz_ReturnsQuizDTO() {
        // Arrange
        Long quizId = 1L;
        Category category = new Category();
        var quiz = quizAIDTO.toQuiz(category);
        quiz.setId(quizId);
        QuizDTO expectedQuizDTO = quizAIDTO.toQuiz(category).toDTO();

        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));

        // Act
        QuizDTO result = quizService.getQuizByIdAsDTO(quizId);

        // Assert
        assertNotNull(result);
        assertEquals(quizId, 1L);
        verify(quizRepository).findById(quizId);
    }

    @Test
    void getQuizByIdAsDTO_NonExistingQuiz_ThrowsQuizNotFoundException() {
        // Arrange
        Long quizId = 1L;
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(QuizNotFoundException.class, () -> quizService.getQuizByIdAsDTO(quizId));
        verify(quizRepository).findById(quizId);
    }

}