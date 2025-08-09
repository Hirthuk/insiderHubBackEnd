package com.insider.backend.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insider.backend.DTO.LoginUserRequest;
import com.insider.backend.entity.UsersEntity;
import com.insider.backend.repositories.UserRepository;
import com.insider.backend.service.UserService;
import com.insider.backend.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public JwtUtil jwtUtuil;
	
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginUserRequest loginUserRequest){
		Long sapid = loginUserRequest.getSapid();
		String password = loginUserRequest.getPassword();
		
		Optional<UsersEntity> existingUser = userRepository.findBySapid(sapid);
		
		if(existingUser.isPresent()) {
			UsersEntity user = existingUser.get();
			if(passwordEncoder.matches(password, user.getPassword())) {
				String role = user.getRole();
				String token = jwtUtuil.generateToken(sapid, role);
				
				return Map.of("role", role, "token", token);
			}
			else {
				 throw new RuntimeException("Invalid credentials");
			}
			
		}
		else {
			throw new RuntimeException("User not found.. Please Sign Up");
		}
		
		
	}

}
