package com.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.profile.model.User;
import com.profile.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;
	

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.repo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Not Found" + username));
		return user;
	}

}
