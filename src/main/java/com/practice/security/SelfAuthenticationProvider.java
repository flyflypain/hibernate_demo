//package com.practice.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SelfAuthenticationProvider implements AuthenticationProvider {
//
//	@Autowired
//	private SelfUserDetailsService userDetailsService;
//
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		String userName = (String) authentication.getPrincipal();
//		String password = (String) authentication.getCredentials();
//
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPwd = passwordEncoder.encode(password);
//
//		UserDetails userInfo = userDetailsService.loadUserByUsername(userName);
//
//		if (!userInfo.getPassword().equals(encodedPwd)) {
//			throw new BadCredentialsException("ID or PW is not correct, please try again");
//		}
//
//		return new UsernamePasswordAuthenticationToken(userName, password, userInfo.getAuthorities());
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		return true;
//	}
//
//}
