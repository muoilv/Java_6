package com.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.poly.entity.Category;
import com.poly.entity.Product;

public interface ProductService {

	Page<Product> findAll(Optional<Integer> page);
	
	Page<Product> findAllByNameProduct(String keyword , Optional<Integer> page);
	
	List<Product> findAllByNameProduct(String keyword);
	
	Product findById(Integer id);
	
	List<Product> findAllByCategory(Category cate);
	
}
