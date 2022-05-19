package com.sjpowa.webapi.restfulwebservices.helloworld;

public class HelloWorldPath {

	private String idk;

	// GETTER
	public String getIdk() {
		return idk;
	}

	// SETTER
	public void setIdk(String idk) {
		this.idk = idk;
	}

	// CTOR
	public HelloWorldPath(String idk) {
		this.idk = idk;
	}

	// toString METHOD
	@Override
	public String toString() {
		return "HelloWorldPath [idk=" + idk + "]";
	}
	
}
