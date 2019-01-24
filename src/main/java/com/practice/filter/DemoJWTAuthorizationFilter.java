//package com.practice.filter;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//@PropertySource(value = "classpath:application.yml")
//public class DemoJWTAuthorizationFilter extends BasicAuthenticationFilter {
//
//	@Value("${security.SIGN_UP_URL}")
//	private String SIGN_UP_URL;
//
//	private AuthenticationManager authenticationManager;
//
//	public DemoJWTAuthorizationFilter(AuthenticationManager authenticationManager) {
//		super(authenticationManager);
//	}
//
//}
