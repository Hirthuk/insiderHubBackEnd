package com.insider.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insider.backend.entity.AppreciationPostEntity;
import com.insider.backend.service.AppreciationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/appreciate")
public class AppreciationController {

	@Autowired
	public AppreciationService appreciateService;
	
	@PostMapping
	public String appreciate(@Valid @RequestBody AppreciationPostEntity post) {
		return appreciateService.createPost(post);
	}
	
	@GetMapping
	public List<AppreciationPostEntity> getPostList() {
		try {
			return appreciateService.getAllPosts();
		}
		catch(Exception e) {
			throw new AccessDeniedException(e.getMessage());
		}
	}
	
	@PostMapping("/like/{id}")
	public void putLike(@PathVariable Long id) {
	    appreciateService.putLike(id);
	}

}
