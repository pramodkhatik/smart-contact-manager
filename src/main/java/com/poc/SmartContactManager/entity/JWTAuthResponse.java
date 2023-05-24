package com.poc.SmartContactManager.entity;

import lombok.Data;

@Data
public class JWTAuthResponse {

	private String token;
	private User user;
}
