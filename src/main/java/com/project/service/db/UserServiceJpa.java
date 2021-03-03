package com.project.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.project.model.User;

import com.project.repository.UsersRepository;
import com.project.service.IUserService;

@Service
@Primary
public class UserServiceJpa implements IUserService {

	@Autowired
	private UsersRepository repoUsers;

	@Override
	public void save(User usuario) {

		repoUsers.save(usuario);
	}

	@Override
	public void delete(Integer idUsuario) {
		repoUsers.deleteById(idUsuario);
	}

	@Override
	public List<User> searchAll() {
		List<User> listUsers = repoUsers.findAll();
		return listUsers;
	}

	@Override
	public User findByUsername(String username) {

		return repoUsers.findByUsername(username);
	}

}
