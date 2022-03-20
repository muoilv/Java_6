package com.poly.rest.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.StatusObjectDAO;
import com.poly.entity.StatusObject;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/status")
public class StatusRestController {

	@Autowired
	StatusObjectDAO sttDAO;
	
	//ArrayList<Product> listPro = new ArrayList<Product>();
	
	@GetMapping
	public ResponseEntity<List<StatusObject>> getAll(Model model){
		
		return ResponseEntity.ok(sttDAO.findAll());
	}
	
//	@GetMapping("/{stt}")
//	public ResponseEntity<List<Bill>> getByStt(@PathVariable("stt") String stt , @RequestParam("name") String key){
//		System.out.println(billDAO.findAllByUserNameUserLikeAndStatusObjectIdStatusLike(key, stt));
//		return ResponseEntity.ok(billDAO.findAllByUserNameUserLikeAndStatusObjectIdStatusLike(key, stt));
//	}
	
	
	
//	@PutMapping("/{id}")
//	public ResponseEntity<Bill> put(@PathVariable("id") Integer id , @RequestBody Bill bill){
//		if(!billDAO.existsById(id)) {
//			return ResponseEntity.notFound().build();
//		}
//		billDAO.save(bill);
//		return ResponseEntity.ok(bill);
//	}

}
