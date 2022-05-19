package com.sjpowa.webapi.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {

	// timestamp
	private Date timestamp;
	
	// message
	private String message;
	
	// detail
	private String details;

	// CTOR
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	// GETTERS
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
}
