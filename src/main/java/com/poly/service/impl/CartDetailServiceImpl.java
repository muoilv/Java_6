package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.CartDetailDAO;
import com.poly.entity.Cart;
import com.poly.entity.CartDetail;
import com.poly.service.CartDetailService;

@Service
public class CartDetailServiceImpl implements CartDetailService{

@Autowired
CartDetailDAO cddao;

@Override
public List<CartDetail> findAllByCart(Cart ca) {
	List<CartDetail> list = cddao.findAllByCart(ca);
	return list;
}



	
}
