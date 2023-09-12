package com.monitech.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.monitech.restapi.exception.ValidationException;
import com.monitech.restapi.model.Category;
import com.monitech.restapi.service.CategoryService;

import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api/favore/v1")
public class CategoryController {

    private final CategoryService categoryService;

    private void validateCategory(Category category) {
        if (category.getCategoryName() == null || category.getCategoryName().isEmpty()) {
            throw new ValidationException("Category name is required");
        }
        if (category.getCategoryName().length() > 255) {
            throw new ValidationException("Category name must not exceed 255 characters");
        }
        if (category.getDescription() == null || category.getDescription().isEmpty()) {
            throw new ValidationException("Description is required");
        }
        if (category.getDescription().length() > 255) {
            throw new ValidationException("Description must not exceed 255 characters");
        }
    }

    private void validateCategoryExistence(Category category) {
        if (categoryService.existsByCategoryName(category.getCategoryName())) {
            throw new ValidationException("Category with name: " + category.getCategoryName() + " already exists");
        }
    }

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //URL: http://localhost:8080/api/favore/v1/categories
    //Method: POST
    @Transactional
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        validateCategory(category);
        validateCategoryExistence(category);

        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    //URL: http://localhost:8080/api/favore/v1/categories
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    //URL: http://localhost:8080/api/favore/v1/categories/{id}
    //Method: GET
    @Transactional
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    //URL: http://localhost:8080/api/favore/v1/categories/{id}
    //Method: PUT
    @Transactional
    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        validateCategory(category);

        Category updatedCategory = categoryService.updateCategory(id, category);
        return ResponseEntity.ok(updatedCategory);
    }

    //URL: http://localhost:8080/api/favore/v1/categories/{id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        Category deletedCategory = categoryService.deleteCategory(id);
        return ResponseEntity.ok(deletedCategory);
    }

}