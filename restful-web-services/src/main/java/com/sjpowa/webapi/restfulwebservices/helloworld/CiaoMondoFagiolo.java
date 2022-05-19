package com.sjpowa.webapi.restfulwebservices.helloworld;

public class CiaoMondoFagiolo {

	private String messaggio;
	
	// GETTER
	public String getMessaggio() {
		return messaggio;
	}
	
	// SETTER
	public void setMezzaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	
	// CTOR
	public CiaoMondoFagiolo(String messaggio) {
		this.messaggio = messaggio;
	}
	
	// for what I see, is useless to write the toString METHOD
	
}
