package com.practice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SelfAuthenticationProvider authenticationProvider;

	@Autowired
	private SelfAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private SelfAuthenticationSuccessHandler authSuccessHandler;

	@Autowired
	private SelfAuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private SelfLogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	private SelfAccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().httpBasic().authenticationEntryPoint(authenticationEntryPoint).and().authorizeRequests()
				.anyRequest().authenticated().and().formLogin().successHandler(authSuccessHandler)
				.failureHandler(authenticationFailureHandler).permitAll().and().logout()
				.logoutSuccessHandler(logoutSuccessHandler).permitAll();

		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

	}

}
