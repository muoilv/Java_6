package com.poly.service;

import com.poly.entity.Cart;
import com.poly.entity.User;

public interface CartService {
	
	Cart findByUser(User us);
	
}
