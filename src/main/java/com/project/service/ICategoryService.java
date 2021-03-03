package com.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.model.Category;

public interface ICategoryService {

	void save(Category categoria);

	List<Category> searchAll();

	Category searchById(Integer idCategory);

	void delete(Integer idCategory);

	Page<Category> searchAll(Pageable page);
}
