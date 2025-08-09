package com.insider.backend.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobaExceptionHandler {
	
//	To Handle general exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIErroDTO> handleGeneralExceptions(Exception ex, HttpServletRequest request) {
		APIErroDTO errorResponse = new APIErroDTO(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal Server Error",
				ex.getMessage(),
				request.getRequestURI())
				;
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
				
	}
	
//	To Handle Validation errors from @Valid from the request body
//	MethodArgumentNotValidException will be raised when the Valid Annotation fails in the RequestBody input
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public ResponseEntity<APIErroDTO> handleValidArgumentException(MethodArgumentNotValidException ex, HttpServletRequest request){
		Map<String,String> details = new HashMap<>();
		
		for(FieldError fe : ex.getBindingResult().getFieldErrors()) {
			details.put(fe.getField(), fe.getDefaultMessage());
		}
		
		APIErroDTO body = new APIErroDTO(
				HttpStatus.BAD_REQUEST.value(),
				"Bad Request",
				"Validation failed", //Using hardcoded string because we don't have custom message created like ResourceNotFound or ConflictException
				request.getRequestURI(),
				details
				);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		
	}
	
}