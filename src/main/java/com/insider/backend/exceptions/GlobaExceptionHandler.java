package com.insider.backend.exceptions;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
	
//	To Handle Validation errors from @Valid from the request body - Error code - 400
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
				"Validation failed", //Using hard coded string because we don't have custom message created like ResourceNotFound or ConflictException
				request.getRequestURI(),
				details
				);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		
	}
	
//	Missing or mismatch type parameters passed to controllers -> @RequestBody we are expecting UserEntity type if it passed wrong or different data type exception will raise
//	Error code 400 HttpStatus.Bad_request.value
	@ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
	public ResponseEntity<APIErroDTO> handleMethodParamsException(Exception ex, HttpServletRequest request) {
		APIErroDTO body = new APIErroDTO(
				HttpStatus.BAD_REQUEST.value(),
				"Bad Request with wrong parameters",
				ex.getMessage(),
				request.getRequestURL().toString()
				);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}
	
//	To Handle the BadCredentials -> Login with wrong password -> Error code 401: Invalid Login credentials
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<APIErroDTO> handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
		APIErroDTO body = new APIErroDTO(
				HttpStatus.UNAUTHORIZED.value(),
				"Invalid Credentials",
				ex.getMessage(),
				request.getRequestURI()
				);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
				
	}
	
//	403 Access denied Exception - Admin access required HttpStatus.forbidden
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<APIErroDTO> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request){
		APIErroDTO body = new APIErroDTO(
				HttpStatus.FORBIDDEN.value(),
				"Access Denied",
				ex.getMessage(),
				request.getRequestURI()
				);
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
	}
	
//	403 NotFoundException(Cu , NotFoundException
	@ExceptionHandler({NotFoundException.class, ResourceNotFoundException.class})
	public ResponseEntity<APIErroDTO> handleNotFoundException(Exception ex, HttpServletRequest request){
		APIErroDTO body = new APIErroDTO(
				HttpStatus.NOT_FOUND.value(),
				"Not Found",
				ex.getMessage(),
				request.getRequestURI()
				);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
	
//	// 409: Conflicts like duplicate keys (use ConflictException in service)
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<APIErroDTO> handleConflictException(ConflictException ex, HttpServletRequest request){
		APIErroDTO body = new APIErroDTO(
				HttpStatus.CONFLICT.value(),
				"Conflict",
				ex.getMessage(),
				request.getRequestURI()
				);
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
	}

}