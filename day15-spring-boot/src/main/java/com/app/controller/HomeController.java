package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	public HomeController() {
		System.out.println("in ctor of "+getClass().getName());
	}
	@RequestMapping("/")
	public String showHomePage()
	{
		System.out.println("in show home page....");
		return "/index";
	}

}
