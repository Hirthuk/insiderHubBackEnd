package com.insider.backend.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.insider.backend.DTO.LoginUserRequest;
import com.insider.backend.entity.UsersEntity;
import com.insider.backend.exceptions.ResourceNotFoundException;
import com.insider.backend.repositories.UserRepository;
import com.insider.backend.util.JwtUtil;

@Service
public class AuthService {
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public JwtUtil jwtUtuil;
	
	public Map<String, Object> login(LoginUserRequest loginUserRequest) {
		Long sapid = loginUserRequest.getSapid();
		String password = loginUserRequest.getPassword();
		

		Optional<UsersEntity> existingUser = userRepository.findBySapid(sapid);
		
				if(existingUser.isPresent()) {
			UsersEntity user = existingUser.get();
//			Debugging commands
//			System.out.println("Hash from DB [" + user.getPassword() + "]");
//			System.out.println(user.getPassword().length());
//			System.out.println("Check  " + passwordEncoder.matches(password, user.getPassword()));
//			System.out.println("Entered password [" + password + "]");
//			System.out.println(passwordEncoder.encode(password));
//			System.out.println(passwordEncoder.encode(password).equals(user.getPassword()));
			if(passwordEncoder.matches(password, user.getPassword())) {
				String role = user.getRole();
				String token = jwtUtuil.generateToken(sapid, role);
				
				return Map.of("role", role, "token", token);
			}
			else {
				 throw new BadCredentialsException("Invalid Credentials");
			}
			
		}
		else {
			throw new ResourceNotFoundException("User not found. Please SignUp to get started");
		}
		
	}

}
