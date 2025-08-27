package com.insider.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insider.backend.entity.AppreciationPostEntity;
import com.insider.backend.entity.UsersEntity;
import com.insider.backend.exceptions.ConflictException;
import com.insider.backend.repositories.AppreciationRepository;
import com.insider.backend.repositories.UserRepository;

@Service
public class AppreciationService {

	@Autowired
	public AppreciationRepository appreciationRepo;
	
	@Autowired
	public UserRepository userRepo;
	
	public String createPost(AppreciationPostEntity post) {
		Optional<UsersEntity> userPresent = userRepo.findBySapid(post.getTo_sapid());
		if(userPresent.isEmpty()) {
			return post.getTo_sapid() + "is not Present";
		}
		try {
			Optional<UsersEntity> userName  = userRepo.findBySapid(post.getTo_sapid());
			post.setGetterName(userName.get().getName());
			appreciationRepo.save(post);
			userRepo.recalculateRanks(); // ensure ranks updated
			userRepo.totalApprecitionIncreate(post.getTo_sapid());
			return "Post added successfully";
		}
		catch(Exception e) {
			throw new ConflictException(e.getMessage());
		}
	}
	
	public List<AppreciationPostEntity> getAllPosts(){
		try {
			return appreciationRepo.findAll();
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void putLike(Long id) {
		try {
			appreciationRepo.updateLikes(id);
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
