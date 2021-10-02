package com.facebook.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class TestController {
	
@RequestMapping(value="/NewFile" , method=RequestMethod.GET)
public String testpage(){
	System.out.println("hit");
	return "NewFile";
}

//@GetMapping("/profile/post")	
//public String post() {
//	
//	return "postFrom";	
//	
//}
}
