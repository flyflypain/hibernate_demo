package com.practice.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.practice.response.AuthResponse;

@Component
public class SelfAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		AuthResponse authReponse = new AuthResponse();

		authReponse.setStatus("400");
		authReponse.setMsg("Login failure");

		response.setContentType("application/json");
		response.getWriter().write(JSON.toJSONString(authReponse));

	}

}
