package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.UserDAO;
import com.poly.entity.User;
import com.poly.service.UserService;

@Service
public class UserServiceImpl implements UserService{
@Autowired
UserDAO udao;

@Override
public User findById(Integer id) {
	return udao.findByIdUser(id);
}

@Override
public User findByEmail(String email) {
	return udao.findByEmailUser(email);
}

@Override
public User findByEmailLike(String email) {
	// TODO Auto-generated method stub
	return udao.findByEmailUser("%"+email+"%");
}
@Override
public List<User> findAllByEmailLike(String email) {
	return udao.findAllByNameUserLike("%"+email+"%");
}

}
