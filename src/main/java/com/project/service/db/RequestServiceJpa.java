package com.project.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.model.Request;

import com.project.repository.RequestRepository;
import com.project.service.IRequestService;

@Service
@Primary
public class RequestServiceJpa implements IRequestService {

	@Autowired
	private RequestRepository requestRepo;

	@Override
	public void save(Request req) {

		requestRepo.save(req);
	}

	@Override
	public void delete(Integer idRequest) {

		requestRepo.deleteById(idRequest);
	}

	@Override
	public List<Request> findAll() {

		return requestRepo.findAll();
	}

	@Override
	public Request findById(Integer idRequest) {

		Optional<Request> req = requestRepo.findById(idRequest);

		if (req.isPresent()) {
			return req.get();
		}
		return null;
	}

	@Override
	public Page<Request> findAll(Pageable page) {
		return requestRepo.findAll(page);
	}

}
