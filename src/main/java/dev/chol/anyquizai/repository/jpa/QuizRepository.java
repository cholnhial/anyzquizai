package dev.chol.anyquizai.repository.jpa;

import dev.chol.anyquizai.domain.jpa.Quiz;
import dev.chol.anyquizai.dto.QuizDTO;
import dev.chol.anyquizai.enumeration.Difficulty;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
