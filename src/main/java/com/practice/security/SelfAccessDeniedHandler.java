package com.practice.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.practice.response.AuthResponse;

@Component
public class SelfAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		AuthResponse authResponse = new AuthResponse();

		authResponse.setStatus("300");
		authResponse.setMsg("Not authorized action. Please check your authorities");

		response.getWriter().write(JSON.toJSONString(authResponse));

	}

}
