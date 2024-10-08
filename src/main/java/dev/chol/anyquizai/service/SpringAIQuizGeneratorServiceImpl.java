package dev.chol.anyquizai.service;

import dev.chol.anyquizai.dto.QuizAIDTO;
import dev.chol.anyquizai.enumeration.Difficulty;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.stereotype.Service;

/**
 * A Spring AI implementation for generating AnyQuizAI quizzes
 */
@RequiredArgsConstructor
@Service
public class SpringAIQuizGeneratorServiceImpl implements AIQuizGeneratorService {
    private final ChatClient chatClient;

    @Override
    public QuizAIDTO generateQuiz(String topic, Difficulty difficulty, Integer numberOfQuestions) {
        var outputParser = new BeanOutputParser<>(QuizAIDTO.class);
        PromptTemplate promptTemplate = new PromptTemplate(QUIZ_PROMPT_TEMPLATE, Map.of("topic", topic, "questions", numberOfQuestions, "difficulty", difficulty, "format", outputParser.getFormat()));
        Prompt prompt = promptTemplate.create();
        Generation generation = chatClient.call(prompt).getResult();
        return outputParser.parse(generation.getOutput().getContent());
    }
}
