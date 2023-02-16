package com.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profile.model.ApiResponse;
import com.profile.model.User;
import com.profile.service.UserServiceImpl;

@RestController
@RequestMapping("/api/")
public class UserController {

	@Autowired
	private UserServiceImpl service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@PostMapping("/add")
	public ResponseEntity<User> AddUser(@RequestBody User user){
		String p=user.getPassword();
		String encrypt=encoder.encode(p);
		user.setPassword(encrypt);
		User createUser = this.service.createUser(user);
		return new ResponseEntity<User>(createUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUsers = this.service.getAllUsers(); 
		return new ResponseEntity<List<User>>(allUsers,HttpStatus.OK);
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<User> GetSingleUser(@PathVariable("id") Integer id){
		User getuserById = this.service.getuserById(id);
		return new ResponseEntity<User> (getuserById,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> UpdateUser(@RequestBody User user,@PathVariable("id") Integer id){
		User updateUser = service.updateUser(user, id);
		return new ResponseEntity<User>(updateUser,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> DeleteUser(@PathVariable("id") Integer id){
		this.service.deleteuser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Succesfully",true),HttpStatus.OK);
	}
	
	
}
