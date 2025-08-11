package com.insider.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.insider.backend.entity.UsersEntity;
import com.insider.backend.exceptions.ConflictException;
import com.insider.backend.repositories.UserRepository;

@Service
public class EmailService {
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	private JavaMailSender mailsender;
	
	public String sendEmailNotification(UsersEntity userentity) {
		
		Optional<UsersEntity> existingUser = userRepository.findBySapid(userentity.getSapid()) ;
		Optional<UsersEntity>	 existingUserbyEmail =	userRepository.findByEmail(userentity.getEmail());
		Optional<UsersEntity> existingUserbyphone = userRepository.findByPhoneNumber(userentity.getPhonenumber());
		Optional<UsersEntity> AdminEmails = userRepository.findByRole("ADMIN");
		if(existingUser.isPresent() || existingUserbyEmail.isPresent() || existingUserbyphone.isPresent()) {
			throw new ConflictException("SAPID or EMAIL or Phone number is already taken");
		}
		String hashedPassword = passwordEncoder.encode(userentity.getPassword());
		userentity.setPassword(hashedPassword);
		try {
			
//			Mail Configuration
			SimpleMailMessage message = new SimpleMailMessage();
			
			message.setFrom("insiderhubnotification@gmail.com");
			message.setTo(AdminEmails.get().getEmail());
			message.setSubject(userentity.getName()+ "  Requested access");
			message.setText(userentity.toString());
			
			mailsender.send(message);
		}
		catch(Exception e) {
			return e.getMessage();
		}
		
		return "Request has been submitted successfully";
	}
}
