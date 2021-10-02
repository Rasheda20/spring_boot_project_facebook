package com.facebook.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.facebook.demo.repo.UserRepository;
import com.facebook.demo.model.User;



@Controller
public class UserController {
	
@Autowired
private	BCryptPasswordEncoder passwordEncoder;

@Autowired
private UserRepository userrepo;	
	
@RequestMapping("/signup")
public String signup(Model model) {
	model.addAttribute("user" , new User());
	return "registration";
}

@RequestMapping(value= "/do_register", method=RequestMethod.POST)
public String registerUser(@ModelAttribute("user") User user, Model model) {
	
	user.setUserRole("ROLE_USER");
	user.setUserEnabled(true);
	user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
	
	User result = this.userrepo.save(user);
	model.addAttribute("user", result);
	
	System.out.println(user);
	return "registration";
}

//handler for custom login
@GetMapping("/signin")
public String customlogin(Model model) {
	
	return "login";
}
}

