package dev.chol.anyquizai.repository.jpa;

import dev.chol.anyquizai.domain.jpa.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByQuizIdOrderByScoreDesc(Long id);
    Optional<Score> findOneByNickName(String nickName);
}
