package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

}
