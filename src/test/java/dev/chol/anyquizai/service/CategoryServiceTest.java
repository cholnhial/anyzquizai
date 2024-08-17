package dev.chol.anyquizai.service;

import dev.chol.anyquizai.domain.jpa.Category;
import dev.chol.anyquizai.exception.CategoryNotFoundException;
import dev.chol.anyquizai.repository.jpa.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCategoryById_ExistingId_ReturnsCategory() {
        // Arrange
        Long id = 1L;
        Category expectedCategory = new Category();
        expectedCategory.setId(id);
        when(categoryRepository.findById(id)).thenReturn(Optional.of(expectedCategory));

        // Act
        Category result = categoryService.getCategoryById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(categoryRepository, times(1)).findById(id);
    }

    @Test
    void getCategoryById_NonExistingId_ThrowsCategoryNotFoundException() {
        // Arrange
        Long id = 1L;
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryById(id));
        verify(categoryRepository, times(1)).findById(id);
    }

    @Test
    void getAll_ReturnsAllCategories() {
        // Arrange
        List<Category> expectedCategories = Arrays.asList(
                new Category(),
                new Category()
        );
        when(categoryRepository.findAll()).thenReturn(expectedCategories);

        // Act
        List<Category> result = categoryService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedCategories.size(), result.size());
        assertEquals(expectedCategories, result);
        verify(categoryRepository, times(1)).findAll();
    }
}