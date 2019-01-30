package com.practice.request;

import java.util.List;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class UserRegisterRequest {

	private String userName;

	private String userPassword;

	@Nullable
	private List<String> roleList;
}
