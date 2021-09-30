package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserRepository;
import com.app.pojos.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	// dependency : dao layer i/f
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> getAllUsers() {
		// invoke dao's method
		return userRepo.findAll();
	}

}
