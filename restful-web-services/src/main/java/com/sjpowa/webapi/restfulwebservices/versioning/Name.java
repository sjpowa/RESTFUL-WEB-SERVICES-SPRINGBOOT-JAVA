package com.sjpowa.webapi.restfulwebservices.versioning;

public class Name {
	
	private String name;
	private String lastName;
	
	// CTOR
	public Name(String name, String lastName) {
		super();
		this.name = name;
		this.lastName = lastName;
	}
	
	// GETTERS and SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
