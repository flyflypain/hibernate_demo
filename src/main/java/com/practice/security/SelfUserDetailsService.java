package com.practice.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.practice.model.Userpool;
import com.practice.repository.UserRepository;

@Component
public class SelfUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public SelfUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

//	The only method that we had to implement is loadUserByUsername. 
//	When a user tries to authenticate, this method receives the username, 
//	searches the database for a record containing it, and (if found) returns an instance of User. 
//	The properties of this instance (username and password) are then checked against the credentials passed by the user in the login request. 
//	This last process is executed outside this class, by the Spring Security framework.

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Userpool applicationUser = userRepository.findByUserName(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		Set<SimpleGrantedAuthority> roleList = applicationUser.getRoleList().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet());

		return new SelfUserDetails(applicationUser.getUserName(), applicationUser.getPassword(), roleList);
	}
}
