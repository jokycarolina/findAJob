package com.project.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.model.Vacant;
import com.project.repository.VacanciesRepository;
import com.project.service.IVacantService;

@Service
@Primary
public class VacanciesServiceJpa implements IVacantService {

	@Autowired
	private VacanciesRepository vacantRepository;

	@Override
	public List<Vacant> searchAll() {

		return vacantRepository.findAll();
	}

	@Override
	public Vacant searchById(Integer idVacant) {

		Optional<Vacant> optional = vacantRepository.findById(idVacant);

		if (optional.isPresent()) {
			return optional.get();
		}

		return null;
	}

	@Override
	public void save(Vacant vacant) {
		vacantRepository.save(vacant);
	}

	@Override
	public List<Vacant> findFeatured() {

		return vacantRepository.findByFavoriteAndStatusOrderByIdDesc(1, "Aprobada");
	}

	@Override
	public void delete(Integer id) {
		vacantRepository.deleteById(id);
	}

	@Override
	public List<Vacant> searchByExample(Example<Vacant> example) {

		return vacantRepository.findAll(example);
	}

	@Override
	public Page<Vacant> findAll(Pageable page) {
		return vacantRepository.findAll(page);
	}

}
