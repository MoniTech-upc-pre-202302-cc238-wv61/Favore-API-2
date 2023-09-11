package com.monitech.restapi.repository;

import com.monitech.restapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByCategoryName(String name);

    Category findByCategoryName(String name);

}