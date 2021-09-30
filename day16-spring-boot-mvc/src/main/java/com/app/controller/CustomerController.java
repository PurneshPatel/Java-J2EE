package com.app.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dao.ITopicDao;
import com.app.pojos.Topic;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	// dependency : DAO layer i/f
	@Autowired
	private ITopicDao topicDao;

	@PostConstruct
	public void init() {
		System.out.println("in init of " + getClass().getName() + " " + topicDao);
	}

	// add request handling method to get all topics n share it with the view layer
	//2 way data binding => POJO  ---> form data n binding form data -->POJO 
	//1 . Create empty (def constr) POJO n add it under model map
	@GetMapping("/topics")
	public String showAllTopics(Model map) {
		System.out.println("in show all topics");
		// invoke dao's method to get all topics
		map.addAttribute("topic_list", topicDao.getAllTopics());
		map.addAttribute("myTopic",new Topic());
		System.out.println(map);
		return "/customer/topics";//LVN --> AVN : /WEB-INF/views/customer/topics/jsp
	}
}