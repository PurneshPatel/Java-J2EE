package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.dto.Customer;
import com.app.dto.LoginRequest;
import com.app.dto.LoginResponse;

@Service
public class BankingClntServiceImpl implements IBankingClntService {
	// dependency : o.s.w.c.RestTemplate (i.e a synchronous rest client class to
	// invoke backend API)
	private RestTemplate template;
	// SpEL : spring expression language
	@Value("${REST_AUTH_URL}")
	private String authUrl;

	@Autowired // autowire=constructor
	public BankingClntServiceImpl(RestTemplateBuilder builder) {
		template = builder.build();
		System.out.println("rest template " + template + " " + authUrl);
	}

	@Override
	public ResponseEntity<LoginResponse> validateBankingCustomer(LoginRequest request) {

		/*
		 * public <T> ResponseEntity<T> postForEntity(String url,
		 * 
		 * @Nullable Object request, Class<T> responseType, Object... uriVariables)
		 * throws RestClientException
		 */
		// use RestTemplate to invoke back end API endpoint
	//	try {
			ResponseEntity<LoginResponse> respEntity = template.postForEntity(authUrl, request, LoginResponse.class);
			System.out.println("resp " + respEntity);
			return respEntity;
	//		return respEntity.getBody();
//		} catch (RuntimeException e) {
//			System.out.println("err in clnt service " + e);
//			return null;
//		}
	}

}
