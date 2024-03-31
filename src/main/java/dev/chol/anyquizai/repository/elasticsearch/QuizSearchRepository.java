package dev.chol.anyquizai.repository.elasticsearch;

import dev.chol.anyquizai.domain.elasticsearch.Quiz;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface QuizSearchRepository extends ElasticsearchRepository<Quiz, String> {

}