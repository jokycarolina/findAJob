package com.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.model.Request;

public interface IRequestService {

	void save(Request req);

	void delete(Integer idRequest);

	List<Request> findAll();

	Request findById(Integer idRequest);

	Page<Request> findAll(Pageable page);

}
