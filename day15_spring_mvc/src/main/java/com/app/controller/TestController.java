package com.app.controller;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // mandatory
@RequestMapping("/test") // optional BUT reco : for the purpose of separation
public class TestController {
	public TestController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	// add a req handling method (method=GET): to understand o.s.w.s.ModelAndView
	@GetMapping("/test1")
	public ModelAndView testModelAndView() {
		System.out.println("in test M n V");
		/*
		 * public ModelAndView(String logicalViewName, String modelAttributeName, Object
		 * modelAttributeValue) Convenient constructor to take a single model object.
		 */
		return new ModelAndView("/test/test1", "server_ts", LocalDateTime.now());
		// Handler ---> ModelAndView obj --> D.S
		// D.S ---> logicalViewName --> V.R
		// Actual view name : /WEB-INF/views/test/test1.jsp
	}

	// add req handling method for testing Model map
	@GetMapping("/test2")
	public String testModelMap(Model modelMap)//empty  map of model attributes
	{
		System.out.println("in test model map " + modelMap);// {}
		// add 2 model attrs in the model map
		modelMap.addAttribute("server_ts", LocalDateTime.now()).
		addAttribute("num_list", Arrays.asList(10, 20, 30, 40));
		System.out.println(modelMap);//populated map
		return "/test/test1";//explicitly rets : logical view name
		//BUT impl. rets : logical view name + map of model attrs
		//LVN(logical view name) : /test/test1
		//What will D.S send to V.R ? ONLY LVN
		//What will V.R rets ? AVN --- Actual view name : /WEB-INF/views/test/test1.jsp
		//Will D.S add any model attrs under req scope ? : YES (2 attrs)
		//JSP : ${requestScope.name}
	}
}
