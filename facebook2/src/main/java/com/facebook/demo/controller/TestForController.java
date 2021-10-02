package com.facebook.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestForController {

	@RequestMapping("/chat1")	
	public String index(Model model) {
		
		return "index";	
		
	}
	
	
	
	@RequestMapping("/template")	
	public String temp(Model model) {
		
		return "template";	
		
	}
	
	@RequestMapping("/")	
	public String intro(Model model) {
		
		return "intro";	
		
	}
	
}
