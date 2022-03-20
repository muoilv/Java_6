package com.poly.rest.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.BillDAO;
import com.poly.dao.BillDetailDAO;
import com.poly.entity.Bill;
import com.poly.entity.BillDetail;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/billdetail")
public class BillDetailRestController {

	@Autowired
	BillDAO billDAO;
	@Autowired
	BillDetailDAO billDTDAO;
	
	@GetMapping
	public ResponseEntity<List<BillDetail>> getAll(){
		return ResponseEntity.ok(billDTDAO.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<BillDetail>> getOne(@PathVariable("id") Integer id){
		Optional<Bill> bill = billDAO.findById(id);
		if(!bill.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(billDTDAO.findByBill(bill.orElse(null)));
	}
}
