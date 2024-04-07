package dev.chol.anyquizai.service;

import dev.chol.anyquizai.dto.QuizAIDTO;
import dev.chol.anyquizai.enumeration.Difficulty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration()
public class AIQuizGeneratorServiceIT {

    @Autowired
    private AIQuizGeneratorService aiQuizGeneratorService;

    @Test
    public void generateQuiz_ShouldReturnQuizDTO_WhenGivenTopicAndNumberOfQuestions() {
        // Arrange
        String topic = "Goats";
        Difficulty difficulty = Difficulty.MEDIUM;
        long numberOfQuestions = 5;

        // Act
        QuizAIDTO generatedQuiz = aiQuizGeneratorService.generateQuiz(topic, difficulty, (int) numberOfQuestions);

        // Assert
        // Check if the generated quiz has the correct topic, difficulty, and number of questions
        assertThat(generatedQuiz.title(),anyOf(containsStringIgnoringCase("goat"),
                containsStringIgnoringCase(("goats"))));
        assertEquals(difficulty, generatedQuiz.difficulty());
        assertEquals(numberOfQuestions, generatedQuiz.questions().size());
    }
}