package dev.chol.anyquizai.web.api;


import dev.chol.anyquizai.dto.QuizDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {

    private final ChatClient chatClient;
    @GetMapping("/quiz")
    public QuizDTO generate(@RequestParam(value = "topic") String topic,
                            @RequestParam("questions") Integer questions,
                            @RequestParam("difficulty") String difficulty) {
        var outputParser = new BeanOutputParser<>(QuizDTO.class);

        String userMessage =
                """
                Generate a {questions} question quiz about {topic} of {difficulty} difficulty (difficulty being EASY, MEDIUM and HARD). Have it such that a question has four answers
                lettered A,B,C, and D. Only one of the answers is marked as correct. Each answer has a title.
                {format}
                """;

        PromptTemplate promptTemplate = new PromptTemplate(userMessage, Map.of("topic", topic,"questions", questions, "difficulty", difficulty, "format", outputParser.getFormat() ));
        Prompt prompt = promptTemplate.create();
        Generation generation = chatClient.call(prompt).getResult();

        return outputParser.parse(generation.getOutput().getContent());
    }
}
