package com.sjpowa.webapi.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sjpowa.webapi.restfulwebservices.user.UserNotFoundException;

// ResponseEntityExceptionHandler
// it's an abstract SPRING CLASS
@ControllerAdvice // used to share thing through all controllers, this is what we need to apply
					// the error to all my Controllers
@RestController // I have to call the RestController cuz this is providing the response back of our exception
public class CustomizedResponseEntityExceptionHandler
extends ResponseEntityExceptionHandler{

	// I want to apply my Customized Error to all Controllers of my project
	// I do it with the ControllerAdvice Annotation
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions
		(Exception ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse =
				new ExceptionResponse(new Date(), ex.getMessage(),
						request.getDescription(false));
	
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException
		(Exception ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse =
				new ExceptionResponse(new Date(), ex.getMessage(),
						request.getDescription(false));
	
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionResponse exceptionResponse =
			new ExceptionResponse(new Date(), "Validation Failed",
					ex.getBindingResult().toString());
		
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
}
