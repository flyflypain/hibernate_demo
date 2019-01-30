package com.practice.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.model.Userpool;
import com.practice.security.SelfUserDetails;

//Note: You might be wondering what class is dealing with the requests issued to the /login endpoint. 
//The answer to this question is simple, the JWTAuthenticationFilter class that 
//you created previously extends UsernamePasswordAuthenticationFilter. This filter, 
//which is provided by Spring Security, registers itself as the responsible for this endpoint. 
//As such, whenever your backend API gets a request to /login, your specialization of this filter 
//(i.e., JWTAuthenticationFilter) goes into action and handles the authentication attempt (through the attemptAuthentication method).

/**
 * 用户登录时使用的认证过滤器
 * 
 * @author lisheng
 *
 */
public class DemoJWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final long EXPIRATION_TIME = 3600l;

	private final String SECRET = "SecretKeyToGenJWTs";

	private final String HEADER_STRING = "Authorization";

	private final String ROLE_KEY = "ROLE";

	@Autowired
	private AuthenticationManager authenticationManager;

	public DemoJWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	/**
	 * 负责处理认证（基于Hash化后的密码）
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			Userpool creds = new ObjectMapper().readValue(request.getInputStream(), Userpool.class);
			System.out.println("JWTAuthentication=======>");
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUserName(),
					creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 认证成功时的处理 ->返回一个token给客户端
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		UserDetails selfUserDetails = (SelfUserDetails) authResult.getPrincipal();
		String username = selfUserDetails.getUsername();
		Collection<? extends GrantedAuthority> authorities = selfUserDetails.getAuthorities();
		String[] roleList = authorities.stream().map(auth -> auth.getAuthority()).collect(Collectors.toList())
				.toArray(new String[authorities.size()]);

		String token = JWT.create().withSubject(username).withArrayClaim(ROLE_KEY, roleList)
				.withExpiresAt(new Date(System.currentTimeMillis() + Long.valueOf(EXPIRATION_TIME).longValue()))
				.sign(Algorithm.HMAC512(SECRET.getBytes()));

		response.setHeader(HEADER_STRING, token);
	}

	/**
	 * 认证失败时的处理
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(400);
		response.setContentType("application/json");
		response.getWriter().write("Authentication error :" + failed.getMessage());
	}

}
