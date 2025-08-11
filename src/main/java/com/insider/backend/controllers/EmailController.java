package com.insider.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insider.backend.entity.UsersEntity;
import com.insider.backend.service.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {
	
	@Autowired
	public EmailService emailService;
	
	@PostMapping
	public String emailController(@RequestBody UsersEntity userEntity) {
		return emailService.sendEmailNotification(userEntity);
		
	}

}
