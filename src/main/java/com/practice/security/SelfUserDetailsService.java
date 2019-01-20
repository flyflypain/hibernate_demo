package com.practice.security;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.practice.model.Role;
import com.practice.model.Userpool;
import com.practice.repository.UserRepository;

@Component
public class SelfUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Userpool user = userRepository.findByUserName(username);
		SelfUserDetails userInfo = new SelfUserDetails();

		String password = user.getPassword();
		List<Role> roleList = user.getRoleList();

		userInfo.setUsername(username);
		userInfo.setPassword(password);

		Set<SimpleGrantedAuthority> authoritiesSet = roleList.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet());

		userInfo.setAuthorities(authoritiesSet);

		return userInfo;
	}

}
