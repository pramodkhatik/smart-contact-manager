package com.poc.SmartContactManager.entity;

import lombok.Data;

@Data
public class JWTAuthRequest {

	private String username;
	
	private String password;
}
