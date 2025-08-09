package com.insider.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.insider.backend.entity.UsersEntity;
import com.insider.backend.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
//	To List All Users
	public List<UsersEntity> getAllUsers() {
		return userRepository.findAll();
	}
	
//	To Create User
	public UsersEntity createUser(UsersEntity user) {
		Optional<UsersEntity> existingUser = userRepository.findBySapid(user.getSapid());
		
		if(existingUser.isPresent()) {
			throw new RuntimeException("User with this SAPID is already exists");
		}
		else {
			String hashedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(hashedPassword);
			return userRepository.save(user);
		}
	}
	
//	To Delete User
	public void deleteUser(Long sapid) {
		
		Optional<UsersEntity> existingUser = userRepository.findBySapid(sapid);
		
		if(existingUser.isPresent()) {
			userRepository.deleteBySapid(sapid);
		}
		else {
			throw new RuntimeException("User is not exist to delete");
		}
	}
	
	
}