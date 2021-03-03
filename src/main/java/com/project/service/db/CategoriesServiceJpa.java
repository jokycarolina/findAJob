package com.project.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.model.Category;
import com.project.repository.CategoriesRepository;
import com.project.service.ICategoryService;

@Service
@Primary
public class CategoriesServiceJpa implements ICategoryService {

	@Autowired
	private CategoriesRepository categoryRepo;

	@Override
	public void save(Category categoria) {
		categoryRepo.save(categoria);

	}

	@Override
	public List<Category> searchAll() {

		return categoryRepo.findAll();
	}

	@Override
	public Category searchById(Integer idCategory) {
		Optional<Category> optional = categoryRepo.findById(idCategory);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void delete(Integer idCategory) {

		categoryRepo.deleteById(idCategory);

	}

	@Override
	public Page<Category> searchAll(Pageable page) {

		return categoryRepo.findAll(page);
	}

}
