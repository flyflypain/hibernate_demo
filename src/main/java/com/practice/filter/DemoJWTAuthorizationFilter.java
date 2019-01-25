package com.practice.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;

public class DemoJWTAuthorizationFilter extends BasicAuthenticationFilter {

	private final long EXPIRATION_TIME = 3600l;

	private final String SECRET = "SecretKeyToGenJWTs";

	private final String HEADER_STRING = "Authorization";

	private final String TOKEN_PREFIX = "Bearer ";

	private AuthenticationManager authenticationManager;

	public DemoJWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String tokenHeader = request.getHeader(HEADER_STRING);
		if (tokenHeader == null || !tokenHeader.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(request, response);
		}
		SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
		chain.doFilter(request, response);

	}

	private Authentication getAuthentication(String tokenHeader) {
		String token = tokenHeader.replace(TOKEN_PREFIX, "");
		String username = JWT.decode(token).getSubject();
		if (username != null) {
			return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
		}
		return null;
	}

}
