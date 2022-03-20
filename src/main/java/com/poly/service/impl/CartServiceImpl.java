package com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.CartDAO;
import com.poly.entity.Cart;
import com.poly.entity.User;
import com.poly.service.CartService;

@Service
public class CartServiceImpl implements CartService{
@Autowired
CartDAO cdao;


@Override
public Cart findByUser(User us) {
	Cart cart = cdao.findByUser(us);
	return cart;
}



	
}
