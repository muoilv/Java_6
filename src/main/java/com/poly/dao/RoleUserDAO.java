package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.RoleUser;

public interface RoleUserDAO extends JpaRepository<RoleUser, Integer> {
	RoleUser findByNameRole(String nameRole);
	
}
