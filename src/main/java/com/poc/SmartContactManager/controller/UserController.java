package com.poc.SmartContactManager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.poc.SmartContactManager.entity.User;
import com.poc.SmartContactManager.service.FileService;
import com.poc.SmartContactManager.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path; 
	
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
	
	@PostMapping("/users/image/upload/{userId}")
	public ResponseEntity<User> updloadImage(@RequestParam MultipartFile image,@PathVariable int userId) throws IOException{
		String imageName = fileService.uploadImage(path, image);
		User user = userService.getUser(userId);
		user.setImageUrl(imageName);
		userService.save(user);
//		System.out.println(user.toString());
		return ResponseEntity.ok(user);
	}
	
	@GetMapping(path="/users/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName,HttpServletResponse response) throws IOException {
		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
}
