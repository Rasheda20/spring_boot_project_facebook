package com.facebook.demo.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.facebook.demo.model.User;
import com.facebook.demo.repo.UserRepository;
import com.facebook.demo.service.EmailService;

@Controller
public class ForgotController {
	Random random = new Random(1000);
	
@Autowired	
private EmailService emailService;

@Autowired	
private UserRepository userRepository;

@Autowired
private BCryptPasswordEncoder bcrypt;

	
//email id form open
	@RequestMapping("/forgot")
	public String openEmailForm(){
	return "forgot";	
	}
	
	@PostMapping("/send-otp")
	public String sendOTP(@RequestParam("email") String email, HttpSession session){
	System.out.println(email);	
	
	//generate otp
	
	int otp = random.nextInt(999999);
	System.out.println("OTP "+otp);
	
	//send email
	
	String subject="OTP From FB";
	String message =""
			+ "<div style='border:1px solid #e2e2e2; padding:20px'>"
			+ "<h1>"
			+ "OTP is "
			+ "<b>"+otp
			+ "</b>"
			+"</h1>"
			+ "</div>";
	String to = email;
	
	boolean flag = this.emailService.sendEmail(subject, message, to);
	if(flag) {
		
		session.setAttribute("myotp", otp);
		session.setAttribute("email", email);
		return "varifyOTP";		
		
	}else {
		
		session.setAttribute("message", "Check your email id");
		return "changePassword";	
		
	}
	
	
	
	}
	
	//verify otp
	
	@PostMapping("/verify-otp") 
	public String verifyOtp(@RequestParam("otp") Integer otp ,HttpSession session){
		int myOtp = (int) session.getAttribute("myotp");
	String email=(String)session.getAttribute("email");
	if(myOtp==otp) {
		
	//password change form
	User user = this.userRepository.getUserByUserName(email);	
	
	
	if(user==null) {
		
		return "forgot";
	}	else {
		
		
	
		
	}
	
	
		
	return "changePassword";	
		
	}else {
		
		session.setAttribute("message", "This OTP is not currect !!!");
		return "varifyOTP";
		
	}
	
	}
	
	
	//change password
	@PostMapping("/change_password")
	public String changepassword(@RequestParam("newpassword") String newpassword, HttpSession session) {
		
		String email=(String)session.getAttribute("email");
		User user = this.userRepository.getUserByUserName(email);
		user.setUserPassword(this.bcrypt.encode(newpassword));
		this.userRepository.save(user);
		
		
		return "redirect:/signin";
		
		
		
	}
	
	  
	 
	
	
	
	
	
}
