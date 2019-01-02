package com.practice.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class DemoFilterImpl extends OncePerRequestFilter implements Ordered {

	public static final int ORDER = 0;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("-----> Demofilter");
//		request = new DemoHttpServletRequestWrapper(request);
		filterChain.doFilter(request, response);
	}

	@Override
	public int getOrder() {
		return ORDER;
	}

}
