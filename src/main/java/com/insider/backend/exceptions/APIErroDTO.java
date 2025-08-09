package com.insider.backend.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public class APIErroDTO {
	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	private Map<String, String> details;
	
//	Default constructor
	public APIErroDTO() {
		
	}
//	Constructor with our details field
   public APIErroDTO(int status, String error, String message, String path) {
	   this.timestamp = LocalDateTime.now();
	   this.status = status;
	   this.error = error;
	   this.message = message;
	   this.path = path;
	   
   }
   
//   Constructor with all fields
   public APIErroDTO(int status, String error, String message, String path, Map<String, String> details) {
	   this.timestamp = LocalDateTime.now();
	   this.status = status;
	   this.message = message;
	   this.path = path;
	   this.details = details;
   }
   public LocalDateTime getTimestamp() {
	return timestamp;
   }
   public void setTimestamp(LocalDateTime timestamp) {
	this.timestamp = timestamp;
   }
   public int getStatus() {
	return status;
   }
   public void setStatus(int status) {
	this.status = status;
   }
   public String getError() {
	return error;
   }
   public void setError(String error) {
	this.error = error;
   }
   public String getMessage() {
	return message;
   }
   public void setMessage(String message) {
	this.message = message;
   }
   public String getPath() {
	return path;
   }
   public void setPath(String path) {
	this.path = path;
   }
   public Map<String, String> getDetails() {
	return details;
   }
   public void setDetails(Map<String, String> details) {
	this.details = details;
   }
   
   
   
}
