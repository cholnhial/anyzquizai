package dev.chol.anyquizai.repository.jpa;

import dev.chol.anyquizai.domain.jpa.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByQuizIdOrderByScoreAsc(Long id);
}
