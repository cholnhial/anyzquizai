package dev.chol.anyquizai.repository.jpa;

import dev.chol.anyquizai.domain.jpa.Score;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByQuizIdOrderByScoreDesc(Long id);

    Optional<Score> findOneByNickNameAndQuizId(String nickName, Long quizId);
}
