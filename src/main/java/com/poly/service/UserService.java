package com.poly.service;

import java.util.List;

import com.poly.entity.User;

public interface UserService {
	
	User findById(Integer id);
	User findByEmail(String email);
	User findByEmailLike(String email);
	
	List<User> findAllByEmailLike(String email) ;
}

