package com.practice.response;

import lombok.Data;

@Data
public class UserResponse extends AbstractResponse {

	public UserResponse() {
		super();
	}

	private boolean registerResult;
}
