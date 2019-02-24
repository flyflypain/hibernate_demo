package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.model.Userpool;
import com.practice.repository.UserRepository;
import com.practice.request.UserRegisterRequest;
import com.practice.response.UserResponse;
import com.practice.response.builder.UserResponseBuilder;
import com.practice.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value = "/register")
	public UserResponse registerUser(@RequestBody UserRegisterRequest request) {
		Userpool result = userService.userRegister(request);
		UserResponse userResponse = new UserResponseBuilder(new UserResponse(), result).build();
		
		return userResponse;
	}

	@GetMapping(value = "/getAllUsers")
	public List<Userpool> getAllUser() {
		List<Userpool> userList = userRepository.findAll();
		
		return userList;

	}

}
