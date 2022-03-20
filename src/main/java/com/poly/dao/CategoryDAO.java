package com.poly.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
	Category findAllByIdCategoryLike(Long id);
	List<Category> findAllByNameCategoryLike(String keyword);
	
	Page<Category> findAllByNameCategoryLike(String keywords, Pageable pageable);
}
