package com.practice.exception;

import lombok.Data;

@Data
public class TestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// error code
	private String code;

	public TestException(String code, String message) {
		super(message);
		this.code = code;
	}

}
