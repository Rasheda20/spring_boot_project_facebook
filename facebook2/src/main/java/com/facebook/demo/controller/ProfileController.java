package com.facebook.demo.controller;

//import java.security.Principal;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.facebook.demo.model.User;
//import com.facebook.demo.repo.UserRepository;
//
//@Controller
//@RequestMapping("/user")
//public class ProfileController {
//	@Autowired
//	private UserRepository userRepository;
//	
//	
//	@RequestMapping("/profile")	
//	public String home(Model model, Principal principal ) {
//		String userName = principal.getName();
//		System.out.println(userName);
//		
//		User user = userRepository.getUserByUserName(userName);
//		System.out.println(user); 
//		model.addAttribute("user", user);
//		return "profile";
//	}	
//}
