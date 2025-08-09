package com.insider.backend.exceptions;

//In the RuntimeException constructor message we are adding our custom message while create new ConflictException(message) exception.
//So we will get the message from the global exception handler
public class ConflictException extends RuntimeException {

	public ConflictException(String message) {
		super(message);
	}
}
