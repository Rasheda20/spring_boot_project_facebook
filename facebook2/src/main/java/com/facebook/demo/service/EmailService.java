package com.facebook.demo.service;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	public boolean sendEmail(String subject, String message, String to) {
		
		boolean f = false;
		String from ="your email";
		//variable for gmail
		String host= "smtp.gmail.com";
		//get the system properties
		Properties properties =System.getProperties();
		
	//setting important information to properties object
		properties.put("mail.smtp.host", host);	
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//step 1:: to get session object....
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("your email", "password");
			}
			
			
		});	
		session.setDebug(true);
		
		//step 2  :: comose message....
		MimeMessage m= new MimeMessage(session);
		try {
			//from email
			m.setFrom(from);
			//adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			//adding subject to message
			m.setSubject(subject);
			//adding text to message
			//m.setText(message);
			m.setContent(message, "text/html");
			
			
			//-----send
			
		//step:3 send the message using transport class
			Transport.send(m);
			System.out.println("sent success..");
			f=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return f;
		}
		
		
	}
	

