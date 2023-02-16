package com.profile.service;

import java.util.List;

import com.profile.model.User;


public interface UserService {
	
	User createUser(User u);
	
	User updateUser(User user,Integer userId);
	
	User getuserById(Integer userId);
	
	List<User> getAllUsers();

	void deleteuser(Integer userId);
}
