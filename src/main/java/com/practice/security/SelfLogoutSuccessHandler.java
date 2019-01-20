package com.practice.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.practice.response.AuthResponse;

@Component
public class SelfLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		AuthResponse authResponse = new AuthResponse();

		authResponse.setStatus("100");
		authResponse.setMsg(("Logout success"));

		response.getWriter().write(JSON.toJSONString(authResponse));

	}

}
