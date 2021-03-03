package com.project.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.model.Category;

@Service
public class CategoryServiceImpl implements ICategoryService {

	private List<Category> list = null;

	public CategoryServiceImpl() {

		list = new LinkedList<Category>();

		Category category1 = new Category();
		category1.setName("Informatica");
		category1.setId(1);
		category1.setDescription("Descripción correspondiente");

		Category category2 = new Category();
		category2.setName("Ventas");
		category2.setId(2);
		category2.setDescription("Descripción correspondiente de ventas");

		Category category3 = new Category();
		category3.setName("Construcción");
		category3.setId(3);
		category3.setDescription("Descripción correspondiente construcción");
		System.out.println(category3);

		Category category4 = new Category();
		category4.setName("Contabilidad");
		category4.setId(4);
		category4.setDescription("Descripción correspondiente contabilidad");

		Category category5 = new Category();
		category5.setName("Administrativo");
		category5.setId(5);
		category5.setDescription("Descripción correspondiente administrativo");

		list.add(category1);
		list.add(category2);
		list.add(category3);
		list.add(category4);
		list.add(category5);
		;

	}

	@Override
	public void save(Category categoria) {

		list.add(categoria);
		System.out.println(categoria);

	}

	@Override
	public List<Category> searchAll() {
		System.out.println(list);
		return list;

	}

	@Override
	public Category searchById(Integer idCategory) {
		for (Category category : list) {
			if (category.getId() == idCategory) {
				return category;
			}
		}
		return null;
	}

	@Override
	public void delete(Integer idCategory) {

	}

	@Override
	public Page<Category> searchAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
