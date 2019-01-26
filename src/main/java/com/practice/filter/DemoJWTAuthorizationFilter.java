package com.practice.filter;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;

public class DemoJWTAuthorizationFilter extends BasicAuthenticationFilter {

	private final String HEADER_STRING = "Accesstoken";

	public DemoJWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String tokenHeader = request.getHeader(HEADER_STRING);
		if (tokenHeader == null) {
			chain.doFilter(request, response);
		} else {
			SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
			chain.doFilter(request, response);
		}

	}

	private Authentication getAuthentication(String tokenHeader) {
		String username = JWT.decode(tokenHeader).getSubject();
		Claim claim = JWT.decode(tokenHeader).getClaim("ROLE");
		List<String> roleStrList = claim.asList(String.class);

		Set<SimpleGrantedAuthority> roleList = roleStrList.stream().map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toSet());
		if (username != null) {
			return new UsernamePasswordAuthenticationToken(username, null, roleList);
		}
		return null;
	}

}
