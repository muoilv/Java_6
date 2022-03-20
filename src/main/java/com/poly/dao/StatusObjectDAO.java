package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.StatusObject;

public interface StatusObjectDAO extends JpaRepository<StatusObject, String> {
	StatusObject findByIdStatus(String id);
}
