package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginRequest;
import com.app.service.IBankingClntService;

@RestController
@RequestMapping("/banking")
public class NetBankingClntController {
	@Autowired
	private IBankingClntService service;
	
	public NetBankingClntController() {
		System.out.println("in ctor "+getClass().getName());
	}
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateBankCustomer(@RequestBody LoginRequest request)
	{
		System.out.println("in auth bank cust "+request);
		return service.validateBankingCustomer(request);
	}
}
