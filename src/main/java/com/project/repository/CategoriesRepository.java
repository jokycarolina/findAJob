package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Category;

public interface CategoriesRepository extends JpaRepository<Category, Integer> {

}
