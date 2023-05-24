package com.poc.SmartContactManager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.SmartContactManager.entity.JWTAuthRequest;
import com.poc.SmartContactManager.entity.JWTAuthResponse;
import com.poc.SmartContactManager.entity.User;
import com.poc.SmartContactManager.security.JWTTokenHelper;
import com.poc.SmartContactManager.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class FormController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> loginUser(@RequestBody JWTAuthRequest jwtAuthRequest) throws Exception{

		authenticate(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());

		
		String token = jwtTokenHelper.generateToken(userDetails);
		
		JWTAuthResponse response = new JWTAuthResponse();
		response.setToken(token);
		response.setUser((User)userDetails);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {	
		User tempUser = userService.save(user);
		return new ResponseEntity<User>(tempUser,HttpStatus.CREATED);
	}
	
	private void authenticate(String username,String password) throws Exception{
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Details");
		}
	}
}
