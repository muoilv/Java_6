package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Cart;
import com.poly.entity.User;


public interface CartDAO extends JpaRepository<Cart, Integer> {
	@Query("SELECT c FROM Cart c WHERE c.user.emailUser=?1")
	Cart findbyUserEmail(String useremail);
	
	Cart findByUser(User user);
}
