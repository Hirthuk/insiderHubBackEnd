package com.insider.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.insider.backend.entity.UserRequestEntity;
import com.insider.backend.entity.UsersEntity;
import com.insider.backend.exceptions.ConflictException;
import com.insider.backend.repositories.UserRepository;
import com.insider.backend.repositories.UserRequestRepository;

@Service
public class UserRequestService {
	
	@Autowired
	public UserRequestRepository userRequestRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
//	Save user Details to userRequest table
	public void saveUserRequest(UserRequestEntity userRequestEntity) {
//		Existing user details 
		Optional<UsersEntity> existingUser = userRepository.findBySapid(userRequestEntity.getSapid()) ;
		Optional<UsersEntity>	 existingUserbyEmail =	userRepository.findByEmail(userRequestEntity.getEmail());
		Optional<UsersEntity> existingUserbyphone = userRepository.findByPhoneNumber(userRequestEntity.getPhone_number());
		
		if(existingUser.isPresent()) {
			Long existingUserSapid = existingUser.get().getSapid();
			throw new ConflictException(String.valueOf(existingUserSapid) + " is already taken");
		}
		
		if(existingUserbyEmail.isPresent()) {
			throw new ConflictException(existingUserbyEmail.get().getEmail() + "  is already taken");
		}
		
		if(existingUserbyphone.isPresent() && !existingUserbyphone.isEmpty() && existingUserbyphone.get().getPhonenumber() != null) {
			throw new ConflictException("Phone number is taken already");
		}
		
		Optional<UserRequestEntity> existingRequestUser = userRequestRepository.findBySapid(userRequestEntity.getSapid());
		
		if(existingRequestUser.isPresent()) {
			throw new ConflictException("User already requested with this Sapid");
		}
		try {
			String encryptedPassword = passwordEncoder.encode(userRequestEntity.getPassword());
			userRequestEntity.setPassword(encryptedPassword);
			userRequestRepository.save(userRequestEntity);
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
//	Get User details
	public List<UserRequestEntity> getUserRequestDetails(){
		try {
			return userRequestRepository.findAll();
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void DeleteUser(Long sapid) {
		try {
			 userRequestRepository.deleteBySapid(sapid);
		}
		catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
