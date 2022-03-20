package com.poly.rest.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.BillDAO;
import com.poly.dao.StatusObjectDAO;
import com.poly.entity.Bill;
import com.poly.entity.StatusObject;
import com.poly.entity.User;
import com.poly.service.BillService;
import com.poly.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bill")
public class BillRestController {

	@Autowired
	BillDAO billDAO;
	@Autowired
	BillService billService;
	@Autowired 
	UserService userService;
	@Autowired 
	StatusObjectDAO sttDAO;
	//ArrayList<Product> listPro = new ArrayList<Product>();
	
	@GetMapping
	public ResponseEntity<List<Bill>> getAll(Model model){
		
		return ResponseEntity.ok(billDAO.findAll());
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<List<Bill>> getbyemail(Model model,@PathVariable("email") String key){
		List<Bill> lstBill = billService.findByEmail(key);
		return ResponseEntity.ok(lstBill);
	}
	
	
	@GetMapping("/filter")
	public ResponseEntity<List<Bill>> getByStt(@RequestParam("search") String search, @RequestParam("stt") String stt,@RequestParam("sort") String sort){
		List<Bill> lstBill = null;
		List<User> lstUser = userService.findAllByEmailLike(search);
		
		if(search.length() > 0 & lstUser.size() > 0) {
			lstBill = billDAO.findByUser(lstUser.get(0));
			if(!stt.equals("")) {
				StatusObject sttOb = sttDAO.findByIdStatus(stt);
				
				if(sort.equals("Asc")) {
					lstBill = billDAO.findByUserAndStatusObjectOrderByTimeAddAsc(lstUser.get(0),sttOb);
				}else {
					lstBill = billDAO.findByUserAndStatusObjectOrderByTimeAddDesc(lstUser.get(0),sttOb);
				}
			}else {
				if(sort.equals("Asc")) {
					lstBill = billDAO.findByUserOrderByTimeAddAsc(lstUser.get(0));
				}else {
					lstBill = billDAO.findByUserOrderByTimeAddDesc(lstUser.get(0));
				}
			}
		}else {
			if(!stt.trim().equals("")) {
				StatusObject sttOb = sttDAO.findByIdStatus(stt);
				
				if(sort.equals("Asc")) {
					lstBill = billDAO.findByStatusObjectOrderByTimeAddAsc(sttOb);
				}else {
					lstBill = billDAO.findByStatusObjectOrderByTimeAddDesc(sttOb);
				}
			}else {
				if(sort.equals("Asc")) {
					lstBill = billDAO.findByOrderByTimeAddAsc();
				}else {
					lstBill = billDAO.findByOrderByTimeAddDesc();
				}
			}
		}
		
		
		
		
		return ResponseEntity.ok(lstBill);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Bill> put(@PathVariable("id") Integer id , @RequestBody Bill bill){
		if(!billDAO.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		billDAO.save(bill);
		return ResponseEntity.ok(bill);
	}
	
	
//	@GetMapping("/findbystt/{stt}")
//	public ResponseEntity<List<Bill>> getByStt(@PathVariable("stt") String stt){
//		StatusObject status = sttDAO.findByIdStatus(stt);
//		return ResponseEntity.ok(billDAO.findByStatusObject(status));
//	}
//	
//	@GetMapping("/sorting/{sort}")
//	public ResponseEntity<List<Bill>> sorting(@PathVariable("sort") String sort){
//		List<Bill> lstBill = null;
//		if(sort.equals("Asc")) {
//			lstBill = billDAO.findByOrderByTimeAddAsc();
//		}else {
//			lstBill = billDAO.findByOrderByTimeAddDesc();
//		}
//		return ResponseEntity.ok(lstBill);
//	}

}
