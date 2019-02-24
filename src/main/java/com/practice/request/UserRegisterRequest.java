package com.practice.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class UserRegisterRequest {

	private String userName;

	@NotNull()
	private String userPassword;

	@Nullable
	private List<String> roleList;
}
