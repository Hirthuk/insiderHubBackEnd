package com.insider.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insider.backend.entity.UserRequestEntity;
import com.insider.backend.repositories.UserRequestRepository;
import com.insider.backend.service.UserRequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/requestUser")
public class UserRequestController {
	
	@Autowired
	public UserRequestRepository userRequestRepository;
	
	@Autowired
	public UserRequestService userRequestService;
	
	@PostMapping
	public void addUserRequest(@Valid @RequestBody UserRequestEntity userRequest) {
		userRequestService.saveUserRequest(userRequest);
	}
	
	@GetMapping("/details")
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserRequestEntity> getUserRequest(){
		try {
			return userRequestService.getUserRequestDetails();
		}
		catch(Exception e) {
			throw new AccessDeniedException(e.getMessage());
		}
	}
	
	@PostMapping("/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteUserRequest(@RequestBody Long sapid) {
		 userRequestRepository.deleteById(sapid);
	}

}
