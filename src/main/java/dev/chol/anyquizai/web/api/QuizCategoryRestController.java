package dev.chol.anyquizai.web.api;

import dev.chol.anyquizai.domain.jpa.Category;
import dev.chol.anyquizai.dto.CategoryDTO;
import dev.chol.anyquizai.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quiz/categories")
@RequiredArgsConstructor
public class QuizCategoryRestController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAll().stream().map(Category::toDTO).toList());
    }
}
