package com.insider.backend.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insider.backend.DTO.LoginUserRequest;
import com.insider.backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	public AuthService authService;
	
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginUserRequest loginUserRequest){
		return authService.login(loginUserRequest);
	}

}
