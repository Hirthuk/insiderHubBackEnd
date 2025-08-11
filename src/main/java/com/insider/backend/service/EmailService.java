package com.insider.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.insider.backend.entity.UsersEntity;
import com.insider.backend.exceptions.ConflictException;
import com.insider.backend.repositories.UserRepository;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	private JavaMailSender mailsender;
	
	@Autowired
	public SpringTemplateEngine templateEngine;
	
	public String sendEmailNotification(UsersEntity userentity) {
		
//		Existing user details and Admin details
		Optional<UsersEntity> existingUser = userRepository.findBySapid(userentity.getSapid()) ;
		Optional<UsersEntity>	 existingUserbyEmail =	userRepository.findByEmail(userentity.getEmail());
		Optional<UsersEntity> existingUserbyphone = userRepository.findByPhoneNumber(userentity.getPhonenumber());
		List<UsersEntity> adminEmails = userRepository.findByRole("ADMIN");
		
		
		if(existingUser.isPresent() || existingUserbyEmail.isPresent() || existingUserbyphone.isPresent()) {
			throw new ConflictException("SAPID or EMAIL or Phone number is already taken");
		}
		String hashedPassword = passwordEncoder.encode(userentity.getPassword());
		userentity.setPassword(hashedPassword);
		String[] toAddresses = adminEmails.stream()
		        .map(UsersEntity::getEmail)
		        .toArray(String[]::new);

		try {
			
//			Creating context for Thymeleaf
			Context context = new Context();
			
			context.setVariable("name", userentity.getName());
			context.setVariable("sapid", userentity.getSapid());
			context.setVariable("email", userentity.getEmail());
			context.setVariable("phoneNumber", userentity.getPhonenumber());
			context.setVariable("project", userentity.getProject_name());
			
//			Process the HtmlTemplate
			String htmlcontent = templateEngine.process("user-request-email", context);
			
			
//			Mail Configuration
			MimeMessage message = mailsender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			helper.setFrom("insiderhubnotification@gmail.com");
			helper.setTo(toAddresses);
			helper.setSubject(userentity.getName() + "  Requested Access");
			helper.setText(htmlcontent, true);
			
			mailsender.send(message);
		}
		catch(Exception e) {
			return e.getMessage();
		}
		
		return "Request has been submitted successfully";
	}
}
