package com.app.service;

import com.app.dto.LoginResponse;

public interface INetBankingService {
 LoginResponse authenticateCustomer(String customerId,String pwd);
}
