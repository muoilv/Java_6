package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.RoleUserDAO;
import com.poly.entity.RoleUser;

@RestController
@RequestMapping("/api/role")
public class UserRestController {
	@Autowired
	RoleUserDAO roleDAO;
	
	@GetMapping
	public ResponseEntity<List<RoleUser>> getAll(Model model){
		return ResponseEntity.ok(roleDAO.findAll());
	}
}
