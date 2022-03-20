package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Gender;

public interface GenderDAO extends JpaRepository<Gender, String> {
	Gender findAllByIdGenderLike(String id);
}
