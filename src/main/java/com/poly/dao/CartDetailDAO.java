package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Cart;
import com.poly.entity.CartDetail;

public interface CartDetailDAO extends JpaRepository<CartDetail, Integer> {
	CartDetailDAO findByCartAndProduct(Long id, Long id2) ;
	
	@Query("select p from CartDetail p where p.cart.user.emailUser=?1")
	List<CartDetail> findcartdetail(String email );
	
	List<CartDetail> findAllByCart(Cart cart);
}
