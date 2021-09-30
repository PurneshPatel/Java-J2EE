package com.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.dto.LoginResponse;

@SpringBootTest
class TestService {
	@Autowired
	private INetBankingService service;

	@Test
	void testAuthenticateCustomer() {
		LoginResponse authenticateCustomer = service.authenticateCustomer("hdfc-1001", "ram#123");
		System.out.println(authenticateCustomer);
		assertEquals(2, authenticateCustomer.getAccounts().size());
	}
	

}
