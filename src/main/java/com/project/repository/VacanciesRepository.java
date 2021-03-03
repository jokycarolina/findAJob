package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Vacant;

public interface VacanciesRepository extends JpaRepository<Vacant, Integer> {

	List<Vacant> findByStatus(String status);

	List<Vacant> findByFavoriteAndStatusOrderByIdDesc(int favorite, String status);

	List<Vacant> findBySalaryBetweenOrderBySalaryDesc(double s1, double s2);

	List<Vacant> findByStatusIn(String[] status);
}
