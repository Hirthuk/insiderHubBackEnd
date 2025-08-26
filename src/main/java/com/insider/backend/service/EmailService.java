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
	
	public String sendEmailTrigger(String[] toAddresses, String name, String html) {
		try {
			MimeMessage message = mailsender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			helper.setFrom("insiderhubnotification@gmail.com");
			helper.setTo(toAddresses);
			helper.setSubject(name + "  Requested Access");
			helper.setText(html, true);
			
			mailsender.send(message);
			return "Request has been submitted successfully";
			
		}
		catch(Exception e) {
			return e.getMessage();
		}
		
	}
	
	public String sendEmailNotification(UsersEntity userentity) {
		
		List<UsersEntity> adminEmails = userRepository.findByRole("ADMIN");
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
			String userhtmlcontent = templateEngine.process("user-notification-email", context);
			
//			Mail Configuration
			String[] userArray = {userentity.getEmail()};
			this.sendEmailTrigger(userArray, userentity.getName(), userhtmlcontent);
			return this.sendEmailTrigger(toAddresses, userentity.getName(), htmlcontent);
			 
			
		}
		catch(Exception e) {
			return e.getMessage();
		}
		
		
	}
}
