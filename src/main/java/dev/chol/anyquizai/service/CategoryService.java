package dev.chol.anyquizai.service;

import dev.chol.anyquizai.domain.Category;
import dev.chol.anyquizai.exception.CategoryNotFoundException;
import dev.chol.anyquizai.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category getCategoryById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException(id));
    }

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }
}
