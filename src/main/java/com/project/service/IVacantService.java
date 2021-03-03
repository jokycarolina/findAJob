package com.project.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.model.Vacant;

public interface IVacantService {

	List<Vacant> searchAll();

	Vacant searchById(Integer idVacant);

	void save(Vacant vacant);

	List<Vacant> findFeatured();

	void delete(Integer id);

	List<Vacant> searchByExample(Example<Vacant> example);

	Page<Vacant> findAll(Pageable page);

}
