package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.BillDAO;
import com.poly.entity.Bill;
import com.poly.entity.User;
import com.poly.service.BillService;
import com.poly.service.UserService;

@Service
public class BillServiceImpl implements BillService{
@Autowired
BillDAO bdao;
@Autowired
UserService userSv;

@Override
public Bill saveBill(Bill bill) {
	Bill b = bdao.save(bill);
	return b;
}

@Override
public List<Bill> findByUser(User us) {
	List<Bill> lstBill = bdao.findByUser(us);
	return lstBill;
}


@Override
public Bill findById(Integer idbill) {
	Optional<Bill> bill = bdao.findById(idbill);
	return bill.orElse(null);
}

@Override
public List<Bill> findByEmail(String email) {
	List<User> lstUser = userSv.findAllByEmailLike(email);
	List<Bill> lstBill = null;
	if(lstUser.size() > 0) {
		lstBill = bdao.findByUser(lstUser.get(0));
		return lstBill;
	}
	return null;
}

	
}
