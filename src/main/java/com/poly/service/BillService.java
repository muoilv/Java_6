package com.poly.service;

import java.util.List;

import com.poly.entity.Bill;
import com.poly.entity.User;

public interface BillService {
	
	Bill saveBill(Bill bill);
	
	List<Bill>  findByUser(User us);
	
	Bill findById(Integer idbill);
	
	List<Bill> findByEmail(String email);
	
}
