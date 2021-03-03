package com.project.service;

import java.util.List;

import com.project.model.User;

public interface IUserService {

	void save(User usuario);

	void delete(Integer idUsuario);

	List<User> searchAll();

	User findByUsername(String username);

}
