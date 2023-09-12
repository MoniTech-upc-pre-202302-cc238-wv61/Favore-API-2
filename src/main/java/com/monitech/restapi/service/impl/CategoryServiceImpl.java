package com.monitech.restapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitech.restapi.model.Category;
import com.monitech.restapi.repository.CategoryRepository;
import com.monitech.restapi.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category categoryToUpdate = categoryRepository.findById(id).orElse(null);
        if (categoryToUpdate != null) {
            categoryToUpdate.setCategoryName(category.getCategoryName());
            categoryToUpdate.setDescription(category.getDescription());
            return categoryRepository.save(categoryToUpdate);
        }
        return null;
    }

    @Override
    public Category deleteCategory(Long id) {
        Category categoryToDelete = categoryRepository.findById(id).orElse(null);
        if (categoryToDelete != null) {
            categoryRepository.delete(categoryToDelete);
            return categoryToDelete;
        }
        return null;
    }

    @Override
    public boolean existsByCategoryName(String name) {
        return categoryRepository.existsByCategoryName(name);
    }

    @Override
    public Category findByCategoryName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

}