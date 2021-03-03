package com.project.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.model.Vacant;

@Service
public class VacantServiceImpl implements IVacantService {

	private List<Vacant> list = null;

	public VacantServiceImpl() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		list = new LinkedList<Vacant>();

		try {
			Vacant vacant1 = new Vacant();
			vacant1.setName("Ingeniero mecanico");
			vacant1.setId(1);
			vacant1.setDescription("Solicitamos un Ing.Civil para disenar un puente peatonal");
			vacant1.setDate(sdf.parse("08-11-2020"));
			vacant1.setSalary(14000.00);
			vacant1.setFavorite(1);
			vacant1.setStatus("Aprobada");
			vacant1.setImage("empresa1.jpg");

			Vacant vacant2 = new Vacant();
			vacant2.setName("Arquitecto");
			vacant2.setId(2);
			vacant2.setDescription("Solicitamos un Arquitecto para la zona de zona norte");
			vacant2.setDate(sdf.parse("07-02-2021"));
			vacant2.setSalary(10000.00);
			vacant2.setFavorite(1);
			vacant2.setStatus("Aprobada");
			vacant2.setImage("empresa2.jpg");

			Vacant vacant3 = new Vacant();
			vacant3.setName("Vendedor");
			vacant3.setId(3);
			vacant3.setDescription("Solicitamos Vendedor para mostrados en Capital");
			vacant3.setDate(sdf.parse("16-10-2021"));
			vacant3.setSalary(6000.00);
			vacant3.setStatus("Aprobada");
			vacant3.setFavorite(0);
			vacant3.setImage("empresa3.jpg");

			Vacant vacant4 = new Vacant();
			vacant4.setName("Ingeniero en Sistema");
			vacant4.setId(4);
			vacant4.setDescription(
					"Solicitamos un Ingeniero en sistema para dar soporte en la departamento de informatica");
			vacant4.setDate(sdf.parse("22-05-2021"));
			vacant4.setSalary(500000.00);
			vacant4.setFavorite(1);
			vacant4.setStatus("Aprobada");
			vacant4.setImage("empresa4.png");

			list.add(vacant1);
			list.add(vacant2);
			list.add(vacant3);
			list.add(vacant4);

		} catch (ParseException e) {
			System.out.println("Error" + e.getMessage());
		}

	}

	@Override
	public List<Vacant> searchAll() {

		return list;
	}

	@Override
	public Vacant searchById(Integer idVacant) {
		for (Vacant v : list) {
			if (v.getId() == idVacant) {
				return v;
			}
		}
		return null;
	}

	@Override
	public void save(Vacant vacant) {
		list.add(vacant);
		System.out.println(vacant);

	}

	@Override
	public List<Vacant> findFeatured() {

		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Vacant> searchByExample(Example<Vacant> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Vacant> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
