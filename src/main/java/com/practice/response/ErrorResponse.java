package com.practice.response;

import com.practice.enums.PracExceptionEnum;

import lombok.Data;

@Data
public class ErrorResponse {

	public ErrorResponse(PracExceptionEnum errorType, String message) {
		this.errorType = errorType;
		this.message = message;
	}

	PracExceptionEnum errorType;

	String message;

}
