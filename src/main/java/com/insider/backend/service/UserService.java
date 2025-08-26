package com.insider.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.insider.backend.DTO.UserProfileDTO;
import com.insider.backend.entity.UsersEntity;
import com.insider.backend.exceptions.ConflictException;
import com.insider.backend.exceptions.ResourceNotFoundException;
import com.insider.backend.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
//	To List All Users
	public List<UsersEntity> getAllUsers() {
		List<UsersEntity> users = userRepository.findAll();
		return users;
	}
	
//	To Create User
	public String createUser(UsersEntity user) {
		Optional<UsersEntity> existingUser = userRepository.findBySapid(user.getSapid());
		
		if(existingUser.isPresent()) {
			throw new ConflictException("User with the SAP ID is already present");
		}
		else {
//			String hashedPassword = passwordEncoder.encode(user.getPassword());
//			user.setPassword(hashedPassword); Will be managed in the User Request action
			UsersEntity newUser = userRepository.save(user);
			return newUser.getName() + "  Has been added successfully";
		}
	}
	
//	To Delete User
	public void deleteUser(Long sapid) {
		
		Optional<UsersEntity> existingUser = userRepository.findBySapid(sapid);
		
		if(existingUser.isPresent()) {
			userRepository.deleteBySapid(sapid);
		}
		else {
			throw new ResourceNotFoundException("User with the sapid is not present" + sapid);
		}
	}
	
//	Get User profile details
	public UserProfileDTO getUserDetails(Long sapid) {
		try {
			Optional<UsersEntity>  userDetails = userRepository.findBySapid(sapid);
			if(!userDetails.isPresent()) {
				 throw new ResourceNotFoundException("SapID is not present");
			}
			UsersEntity userEntity = userDetails.get();
			
			UserProfileDTO userProfile = new UserProfileDTO (userEntity.getName(), userEntity.getDesignation(), userEntity.getSapid(), userEntity.getPhonenumber(), 
					userEntity.getProject_name(),userEntity.getTotal_appreciation(), userEntity.getRank());
			
			return userProfile;
		}
		catch(Exception e) {
			throw new BadCredentialsException(e.getMessage());
		}
		
	}
	
//	Get  users Details based on role
	public List<UsersEntity> getUsersByRole(String role){
		try {
			return userRepository.findByRole(role);
		}
		catch(Exception e) {
			throw new RuntimeException("Internal error");
		}
	}
	
	
	
	
}