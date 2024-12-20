package dev.chol.anyquizai.service;

import dev.chol.anyquizai.dto.QuizAIDTO;
import dev.chol.anyquizai.enumeration.Difficulty;

public interface AIQuizGeneratorService {
    String QUIZ_PROMPT_TEMPLATE = """
            Generate a {questions} question quiz about {topic} of {difficulty} difficulty (difficulty being EASY, MEDIUM and HARD). Have it such that a question has four answers
            lettered A,B,C, and D. Only one of the answers is marked as correct. Each answer has a title. Also create a prompt for thumbnail generation that best represents the contents of the quiz.
            {format}
            """;

    QuizAIDTO generateQuiz(String topic, Difficulty difficulty, Integer numberOfQuestions);
}
