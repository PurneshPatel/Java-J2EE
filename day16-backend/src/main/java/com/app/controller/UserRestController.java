package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.User;
import com.app.service.IUserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserRestController {
	//dependency : service layer i/f
	@Autowired
	private IUserService userService;
	public UserRestController() {
		System.out.println("in ctor " + getClass().getName());
	}
	//add REST API endpoint : for getting all users 
	@GetMapping
	public List<User> fetchAllUsers()
	{
		System.out.println("in fetch all users");
		return userService.getAllUsers();
	}
}
