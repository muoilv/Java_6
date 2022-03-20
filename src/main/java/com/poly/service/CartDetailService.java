package com.poly.service;

import java.util.List;

import com.poly.entity.Cart;
import com.poly.entity.CartDetail;

public interface CartDetailService {
	
	List<CartDetail> findAllByCart(Cart ca);
	
}
