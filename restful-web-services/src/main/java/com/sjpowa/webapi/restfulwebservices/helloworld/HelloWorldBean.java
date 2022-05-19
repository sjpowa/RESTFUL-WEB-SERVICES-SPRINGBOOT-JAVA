package com.sjpowa.webapi.restfulwebservices.helloworld;

public class HelloWorldBean {

	private String message;
	
	// GETTER
	public String getMessage() {
		return message;
	}
	
	// SETTER
	public void setMessage(String message) {
		this.message = message;
	}
	
	// CTOR
	public HelloWorldBean(String message) {
		this.message = message;
	}

	// toString METHOD
	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
	
}
