package com.gsuna.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gsuna.project.entity.User;
import com.gsuna.project.repository.UserRepository;
import com.gsuna.project.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

	@Override
	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
