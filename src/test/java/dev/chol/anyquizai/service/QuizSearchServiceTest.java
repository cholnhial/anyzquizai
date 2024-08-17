package dev.chol.anyquizai.service;

import dev.chol.anyquizai.dto.SearchDTO;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.Criteria;

import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuizSearchServiceTest {

    private final QuizSearchService quizSearchService = new QuizSearchService(null);
    @Test
    public void testBuildCriteria_AllFields() {
        SearchDTO searchDto = SearchDTO.builder()
                .title("Java")
                .categoryId("1")
                .numberOfQuestions(10)
                .difficulty("Hard")
                .size(10)
                .page(0)
                .sortByCreatedDate(Sort.Direction.ASC)
                .sortByDifficulty(Sort.Direction.DESC)
                .build();

        Criteria criteria = quizSearchService.buildCriteria(searchDto);

        var fieldWithEntries = criteria.getCriteriaChain()
                .stream()
                .flatMap(fc -> Map.of(fc.getField().getName(), fc.getQueryCriteriaEntries()
                        .stream().findFirst().orElse(null)).entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

        assertEquals("*Java*", fieldWithEntries.get("title").getValue());
        assertEquals(Criteria.OperationKey.MATCHES, fieldWithEntries.get("title").getKey());

        assertEquals("1", fieldWithEntries.get("categoryId").getValue());
        assertEquals(Criteria.OperationKey.EQUALS, fieldWithEntries.get("categoryId").getKey());

        assertEquals(10, fieldWithEntries.get("questions").getValue());
        assertEquals(Criteria.OperationKey.EQUALS, fieldWithEntries.get("questions").getKey());

        assertEquals("Hard", fieldWithEntries.get("difficulty").getValue());
        assertEquals(Criteria.OperationKey.EQUALS, fieldWithEntries.get("difficulty").getKey());
    }
}