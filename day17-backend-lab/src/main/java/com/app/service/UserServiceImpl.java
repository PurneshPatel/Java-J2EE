package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.UserHandlingException;
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

	@Override
	public User addUser(User user) {

		return userRepo.save(user);
	}

	@Override
	public String deleteUser(int userId) {
		userRepo.deleteById(userId);
		return "User details deleted for ID=" + userId;
	}

	@Override
	public User getDetails(int userId) {
		// Method of CrudRepository :
		/*
		 * findById Optional<T> findById(ID id) Rets Optional with entity in case of id
		 * found or rets empty Optional
		 */
		return userRepo.findById(userId).
				orElseThrow(() -> new UserHandlingException("Invalid User ID !!!!!"));
		
	}

	@Override
	public User updateDetails(User detachedUser) {
		return userRepo.save(detachedUser);
	}
	

	
	

	// when the method annotated with @Transactional rets : JPA provider (currently
	// hibernate will end the tx
	// It will auto commit the Tx : in case of no RuntimeException or in case of exc
	// : auto rollback occurs.

}
