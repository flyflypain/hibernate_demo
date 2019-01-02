//package com.practice.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//
//public class DemoHttpServletRequestWrapper extends HttpServletRequestWrapper {
//
//	private HttpServletRequest request; // 所有参数的Map集合
//
//	public DemoHttpServletRequestWrapper(HttpServletRequest request) {
//		super(request);
//		this.request = request;
//	}
//
//	@Override
//	public String[] getParameterValues(String name) {
//		String[] results = request.getParameterValues(name);
//		if (results == null || results.length <= 0) {
//			return null;
//		}
//		System.out.println(results.toString());
//		for (int i = 0; i < results.length; i++) {
//			System.out.println("preWarp: " + results[i]);
//			results[i] += "postWarp";
//			System.out.println(results[i]);
//		}
//		return results;
//	}
//}
