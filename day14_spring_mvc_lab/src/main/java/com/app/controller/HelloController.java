package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // mandatory : To tell SC , following class is a Spring bean , containing
			// request handling logic
public class HelloController {
	public HelloController() {
		System.out.println("in ctor of "+getClass().getName());
	}
	//To tell SC : following method is a req handling method
	@RequestMapping("/hello")
	public String sayHello()
	{
		System.out.println("in say hello");
		return "/welcome";//logical view name(forward view name)
	}
	
	

}
