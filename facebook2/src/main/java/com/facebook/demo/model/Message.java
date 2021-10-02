package com.facebook.demo.model;

public class Message {
	private String name;
	private String Content;
	
	public Message(String name, String content) {
		
		this.name = name;
		this.Content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	
	
	
}
