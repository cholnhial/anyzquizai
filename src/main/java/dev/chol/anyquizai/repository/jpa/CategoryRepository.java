package dev.chol.anyquizai.repository.jpa;

import dev.chol.anyquizai.domain.jpa.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
