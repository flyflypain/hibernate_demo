package com.practice.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.model.Userpool;

public class DemoJWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final long EXPIRATION_TIME = 3600l;

	private final String SECRET = "SecretKeyToGenJWTs";

	private final String HEADER_STRING = "Authorization";

	private final String TOKEN_PREFIX = "Bearer ";

	@Autowired
	private AuthenticationManager authenticationManager;

	public DemoJWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			Userpool creds = new ObjectMapper().readValue(request.getInputStream(), Userpool.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUserName(),
					creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String token = JWT.create().withSubject(((User) authResult.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + Long.valueOf(EXPIRATION_TIME).longValue()))
				.sign(Algorithm.HMAC512(SECRET.getBytes()));

		response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}

}
