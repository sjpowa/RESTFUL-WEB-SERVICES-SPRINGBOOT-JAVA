package com.sjpowa.webapi.restfulwebservices.versioning;

public class PersonV1 {

	private String name;
	
	// CTOR
	public PersonV1(String name) {
		this.name = name;
	}
	
	// GETTERS and SETTERS
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
