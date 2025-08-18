package com.insider.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insider.backend.DTO.UserProfileDTO;
import com.insider.backend.entity.UsersEntity;
import com.insider.backend.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	String admin = "ADMIN";
	String user = "USER";
			
	@Autowired
	public UserService userService;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<UsersEntity> getAllUsers(){
		return userService.getAllUsers();
	} 
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public String createUser(@Valid @RequestBody UsersEntity user) {
		return userService.createUser(user);
	}
	
	@DeleteMapping("/{sapid}")
	@PreAuthorize("hasRole('ADMIN')")
	public String DeleteUser(@PathVariable Long sapid) {
		userService.deleteUser(sapid);
		
		return "User is deleted successfully";
	}
	
	@GetMapping("/profile")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public UserProfileDTO getProfileDetails(Authentication authentication) {
			return userService.getUserDetails(Long.valueOf(authentication.getName()));
		
	}
//	public UserProfileDTO getProfileDetails(@RequestParam Long sapid) {
//		return userService.getUserDetails(sapid);
//	}
	
  @GetMapping("/adminusers")
  @PreAuthorize("hasRole('ADMIN')")
  public List<UsersEntity> getAdminUsers(String admin){
	  try {
		  return userService.getUsersByRole(admin);
	  }
	  catch(Exception e){
		  throw new AccessDeniedException(e.getMessage());
	  }
  }
  
  @GetMapping("/userroleusers")
  @PreAuthorize("hasRole('ADMIN')")
  public List<UsersEntity> getUserRoleUsers(String user){
	  try {
		  return userService.getUsersByRole(user);
	  }
	  catch(Exception e) {
		   throw new AccessDeniedException(e.getMessage());
	  }
  }
	
}