package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginRequest;
import com.app.service.INetBankingService;

@RestController
@RequestMapping("/bank")
public class BankController {
	@Autowired
	private INetBankingService service;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateCustomer(@RequestBody LoginRequest request) {
		System.out.println("in auth cust " + request);
		return ResponseEntity.ok(service.authenticateCustomer(request.getCustomerId(), request.getPassword()));
	}

}
