package com.poc.SmartContactManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.SmartContactManager.entity.User;
import com.poc.SmartContactManager.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = userService.getUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUser(@PathVariable int userId){
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		userService.save(user);
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/users")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		userService.updateUser(user);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId){
		userService.deleteUser(userId);
		return ResponseEntity.ok("User Deleted with User Id : "+userId);
	}
}
