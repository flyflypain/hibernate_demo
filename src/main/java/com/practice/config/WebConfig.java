//package com.practice.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.practice.interceptor.DemoIntecerptor;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new DemoIntecerptor()).addPathPatterns("/*");
//		WebMvcConfigurer.super.addInterceptors(registry);
//	}
//
//}
