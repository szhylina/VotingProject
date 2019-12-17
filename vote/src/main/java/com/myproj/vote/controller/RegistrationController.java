package com.myproj.vote.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.myproj.vote.domain.Role;
import com.myproj.vote.domain.User;
import com.myproj.vote.repos.UserRepo;

@Controller
public class RegistrationController {
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping
	public String greeting(Map<String, Object> model) {
		return "home";
	}
	
	@GetMapping("/registration")
	private String registration() {
		return "registration";
	}
	
	@PostMapping("/registration")
	private String addUser(User user, Map<String, Object> model) {
		User userFromDB = userRepo.findByUsername(user.getUsername());
		if (userFromDB != null)
		{
			model.put("success", "This user is already registered.");
			return "registration";
		}
		user.setRole(Collections.singleton(Role.USER));
		userRepo.save(user);
		return "redirect:/login";
	}
}
