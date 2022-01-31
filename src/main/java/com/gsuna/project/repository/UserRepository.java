package com.gsuna.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsuna.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long >{

	User findByUsername(String username) ;
}
