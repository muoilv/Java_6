package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.dao.ProductDAO;
import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
@Autowired
ProductDAO pdao;

@Override
public Page<Product> findAll(Optional<Integer> page) {
	Page<Product> proPage;
	try {
		Pageable pageable = PageRequest.of(page.orElse(0), 8);
		proPage = pdao.findAll(pageable);
	} catch (Exception e) {
		Pageable pageable = PageRequest.of(0, 8);
		proPage = pdao.findAll(pageable);
	}
	
	return proPage;
}

@Override
public Product findById(Integer id) {
	Optional<Product> pro = pdao.findById(id);
	return pro.orElse(null);
}

@Override
public List<Product> findAllByCategory(Category cate) {
	List<Product> listProByCate = pdao.findAllByCategory(cate);
	return listProByCate;
}

@Override
public Page<Product> findAllByNameProduct(String keyword, Optional<Integer> page) {
	Page<Product> proPage;
	try {
		Pageable pageable = PageRequest.of(page.orElse(0), 8);
		proPage = pdao.findAllByNameProductLike("%" + keyword + "%",pageable);
	} catch (Exception e) {
		Pageable pageable = PageRequest.of(0, 8);
		proPage = pdao.findAllByNameProductLike("%" + keyword + "%",pageable);
	}
	return proPage;
}

@Override
public List<Product> findAllByNameProduct(String keyword) {
	return pdao.findAllByNameProductLike("%" + keyword + "%");
}



	
}
