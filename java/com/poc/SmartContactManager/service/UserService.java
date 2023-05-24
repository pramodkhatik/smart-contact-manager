package com.poc.SmartContactManager.service;

import java.util.List;

import com.poc.SmartContactManager.entity.User;

public interface UserService {

	User save(User user);
	 
	 List<User> getUsers();
	 
	 User getUser(int userId);
	 
	 User updateUser(User user);
	 
	 void deleteUser(int userId);
}
