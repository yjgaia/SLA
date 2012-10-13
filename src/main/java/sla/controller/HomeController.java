package sla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sla.service.VisitCountService;


@Controller
public class HomeController {
	@Autowired
	VisitCountService visitCountService;
	@RequestMapping("/")
	public String home() {
		System.out.println(visitCountService.increaseVisitCount("oksktank/http://naver.com/1"));
		return "home";
	}
}
