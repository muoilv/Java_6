package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.UserDAO;
import com.poly.entity.User;

@RestController
@RequestMapping("/api/user")
public class RoleRestController {
	@Autowired
	UserDAO userDAO;
	
	@GetMapping
	public ResponseEntity<List<User>> getAll(Model model){
		return ResponseEntity.ok(userDAO.findAll());
	}
}
