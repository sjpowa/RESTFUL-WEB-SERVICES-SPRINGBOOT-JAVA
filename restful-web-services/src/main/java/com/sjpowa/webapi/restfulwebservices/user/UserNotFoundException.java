package com.sjpowa.webapi.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// REMEMBER: TRY TO USE IN BIG PROJECTS THE SAME MESSAGE FOR THE SAME ERROR

// So with this Annotation we can have the Response NOT FOUND
// instead of ' 500 Internal Server Error '
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		super(message);
	}
	
	public String toString() {
		return "Ciao";
	}
	
}
