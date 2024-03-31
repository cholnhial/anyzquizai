package dev.chol.anyquizai.service;

import dev.chol.anyquizai.enumeration.Difficulty;
import dev.chol.anyquizai.dto.QuizDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsStringIgnoringCase;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude= DataSourceAutoConfiguration.class)
public class AIQuizGeneratorServiceIT {

    @Autowired
    private AIQuizGeneratorService aiQuizGeneratorService;

    @Test
    public void generateQuiz_ShouldReturnQuizDTO_WhenGivenTopicAndNumberOfQuestions() {
        // Arrange
        String topic = "Goats";
        Difficulty difficulty = Difficulty.MEDIUM;
        Integer numberOfQuestions = 5;

        // Act
        QuizDTO generatedQuiz = aiQuizGeneratorService.generateQuiz(topic, difficulty, numberOfQuestions);

        // Assert
        // Check if the generated quiz has the correct topic, difficulty, and number of questions
        assertThat(generatedQuiz.getTitle(),anyOf(containsStringIgnoringCase("goat"),
                containsStringIgnoringCase(("goats"))));
        assertEquals(difficulty, generatedQuiz.getDifficulty());
        assertEquals(numberOfQuestions, generatedQuiz.getQuestions().size());
    }
}