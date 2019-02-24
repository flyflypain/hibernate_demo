package com.practice.response;

import java.util.List;

import lombok.Data;

@Data
public class UserResponse extends AbstractResponse {

	public UserResponse() {
		super();
	}

	private String username;
	
	private List<String> roleList;
	
	private boolean registerResult;
}
