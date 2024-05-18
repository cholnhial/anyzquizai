package dev.chol.anyquizai.service;

import dev.chol.anyquizai.domain.elasticsearch.Quiz;
import dev.chol.anyquizai.dto.SearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizSearchService {

    private static final String QUIZ_INDEX_NAME = "quiz_index";

    private final ElasticsearchOperations elasticsearchOperations;

    /**
     * Uses search parameters provided to find the quizzes that match
     *
     * @param searchDto DTO containing search parameters
     * @return
     */
    public Page<Quiz> processSearch(SearchDTO searchDto) {
        Criteria criteria = new Criteria("title");
        if (searchDto.getTitle() != null) {
            criteria.contains(searchDto.getTitle());
        }

        if (searchDto.getCategoryId() != null) {
            criteria.and(new Criteria("categoryId").is(searchDto.getCategoryId()));
        }

        if (searchDto.getNumberOfQuestions() != null) {
            criteria.and(new Criteria("questions").is(searchDto.getNumberOfQuestions()));
        }

        if (searchDto.getDifficulty() != null) {
            criteria.and(new Criteria("difficulty").is(searchDto.getDifficulty()));
        }
        Query searchQuery = new CriteriaQuery(criteria);

        if (searchDto.getSortByCreatedDate() != null)  {
            searchQuery.addSort(Sort.by(searchDto.getSortByCreatedDate(), "created"));
        }

        if (searchDto.getSortByDifficulty() != null) {
            searchQuery.addSort(Sort.by(searchDto.getSortByDifficulty(), "_difficulty"));
        }

        Pageable pageable;
        if (searchDto.getPage() != null && searchDto.getSize() != null) {
            pageable = Pageable.ofSize(searchDto.getSize()).withPage(searchDto.getPage());
            searchQuery.setPageable(pageable);
        } else {
            pageable = Pageable.unpaged();
        }

        SearchHits<Quiz> quizzes = elasticsearchOperations
                .search(searchQuery,
                        Quiz.class,
                        IndexCoordinates.of(QUIZ_INDEX_NAME));

        return  new PageImpl<>(quizzes.get().map(SearchHit::getContent).collect(Collectors.toList()),pageable,
                (int) quizzes.getTotalHits());

    }


    /**
     * Creates a quiz index to make search easy later
     *
     * @param quiz the elastic quiz document to save
     * @return the id of the persisted doc
     */
    public String createQuizIndex(Quiz quiz) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(quiz.getId().toString())
                .withObject(quiz).build();

        return elasticsearchOperations
                .index(indexQuery, IndexCoordinates.of(QUIZ_INDEX_NAME));
    }
}
