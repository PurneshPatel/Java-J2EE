package com.app.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dao.ITopicDao;
import com.app.pojos.Topic;

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
		return "/customer/topics";//LVN --> AVN : /WEB-INF/views/customer/topics.jsp
	}
	//add a req handling method for getting selected topic id n generate list of tuts fro the topic
	//http://localhost:8080/day16_spring_boot_mvc/customer/tutorials?id=4  method : GET
	@GetMapping("/tutorials")
	public String showTutorialsByTopic(@ModelAttribute(name = "myTopic")  Topic topic1)
	{
		System.out.println("in show tuts "+topic1);//Topic : id : 4   topicName : null
		return "/customer/tutorials";
	}

}
