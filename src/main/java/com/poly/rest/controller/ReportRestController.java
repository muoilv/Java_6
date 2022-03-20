package com.poly.rest.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.BillDAO;
import com.poly.dao.BillDetailDAO;
import com.poly.dao.CategoryDAO;
import com.poly.dao.ProductDAO;
import com.poly.entity.Report;
import com.poly.entity.Total;

@CrossOrigin("*")
@RestController

public class ReportRestController {

	@Autowired
	BillDetailDAO billDetailDao;
	@Autowired
	ProductDAO productDao;
	@Autowired
	BillDAO billDao;
	@Autowired
	CategoryDAO cateDao;
	
	@GetMapping("/api/report")
	public ResponseEntity<List<Report>> report(Model model){
		return ResponseEntity.ok(billDetailDao.getInventoryByCategory());
	}	
	
	@GetMapping("/api/total")
	public ResponseEntity<Total> total(Model model){
		Total total = new Total();
		total.setTotalBill(billDao.selectQuantity());
		total.setTotalMoney(billDetailDao.sumMoney());
		total.setTotalPro(productDao.selectQuantity());
		
		return ResponseEntity.ok(total);
	}
}


