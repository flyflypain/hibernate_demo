package com.practice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.model.Role;
import com.practice.model.Userpool;
import com.practice.repository.UserRepository;
import com.practice.request.UserRegisterRequest;
import com.practice.response.UserResponse;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value = "/register")
	public UserResponse registerUser(@RequestBody UserRegisterRequest request) {

		Userpool user = new Userpool();
		user.setUserName(request.getUserName());
		user.setPassword(passwordEncoder.encode(request.getUserPassword()));
		List<Role> roleList = request.getRoleList().stream().map(roleStr -> new Role(roleStr))
				.collect(Collectors.toList());
		user.setRoleList(roleList);
		UserResponse userResponse = new UserResponse();

		try {
			userRepository.saveAndFlush(user);
			userResponse.setRegisterResult(true);
		} catch (Exception e) {
			userResponse.setRegisterResult(false);

		}
		return userResponse;
	}

	@GetMapping(value = "/getAllUsers")
	public List<Userpool> getAllUser() {

		List<Userpool> userList = userRepository.findAll();
		return userList;

	}

}
