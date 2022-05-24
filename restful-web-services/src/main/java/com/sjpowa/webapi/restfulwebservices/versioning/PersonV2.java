package com.sjpowa.webapi.restfulwebservices.versioning;

public class PersonV2 {

	private Name name;

	// CTOR
	public PersonV2(Name name) {
		this.name = name;
	}
	
	// GETTERS and SETTERS
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
}
