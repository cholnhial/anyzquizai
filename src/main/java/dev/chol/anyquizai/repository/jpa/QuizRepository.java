package dev.chol.anyquizai.repository.jpa;

import dev.chol.anyquizai.domain.jpa.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
