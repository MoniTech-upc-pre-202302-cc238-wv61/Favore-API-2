package com.monitech.restapi.service;

import java.util.List;

import com.monitech.restapi.model.Category;

public interface CategoryService {

    Category createCategory(Category category);

    Category updateCategory(Long id, Category category);

    Category getCategoryById(Long id);

    boolean existsByCategoryName(String name);

    Category deleteCategory(Long id);

    List<Category> getAllCategories();

    Category findByCategoryName(String name);

}