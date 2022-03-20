package com.poly.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Category;
import com.poly.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	Product findAllByIdProduct(Long id);
	
	List<Product> findAllByNameProductLike(String keyword);
	
	Page<Product> findAllByNameProductLike(String keywords, Pageable pageable);
	
	@Query(value = "SELECT COUNT(id_product) AS SLPro FROM Product", nativeQuery = true)
	Integer selectQuantity();

	List<Product> findAllByCategory(Category cate);
	

}
