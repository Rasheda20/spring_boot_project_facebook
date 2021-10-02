package com.facebook.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facebook.demo.model.Message;

@RestController
public class MessageCController {
	
	@MessageMapping("/message")
	@SendTo("/topic/return-to")
	public Message getContent(@RequestBody Message message) {
		
//		try {
//			
//			Thread.sleep(2000);
//			
//		}catch(InterruptedException e){
//			e.printStackTrace();
//			
//		}
		
		
		return message;
		
	}
	
	
}
