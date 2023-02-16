package com.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profile.Exception.ResourceNotFoundException;
import com.profile.model.User;
import com.profile.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public User createUser(User u) {
		
		return this.repo.save(u);
	}

	@Override
	public User updateUser(User user, Integer userId) {
		User u=this.repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id",userId));
		u.setFirstname(user.getFirstname());
		u.setLastname(user.getLastname());
		u.setEmail(user.getEmail());
		u.setMobile(user.getMobile());

		return this.repo.save(u);
	}

	public User getuserById(Integer userId) {
		User u=this.repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id",userId));
		return u;
	}

	@Override
	public List<User> getAllUsers() {
		return this.repo.findAll();
	}

	@Override
	public void deleteuser(Integer userId) {
		repo.deleteById(userId);
		
	}
	


}
