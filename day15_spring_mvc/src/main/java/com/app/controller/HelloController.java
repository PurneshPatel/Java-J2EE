package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	public HelloController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	@RequestMapping("/hello")
	public String sayHello() {
		System.out.println("in say hello");
		return "/welcome";// Handler rets logical view name to ---> D.S --->V.R ---> Actual view
							// ---forwards the clnt to actual view name
	}
	
}
