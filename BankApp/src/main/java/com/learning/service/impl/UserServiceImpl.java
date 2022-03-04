package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.User;
import com.learning.repository.UserRepository;
import com.learning.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository repo;
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return repo.save(user);
	}

	@Override
	public Optional<User> getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsersAscOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsersDescOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsbyId(long id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}

}
