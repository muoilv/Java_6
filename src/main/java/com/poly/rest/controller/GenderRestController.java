package com.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.GenderDAO;
import com.poly.entity.Gender;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/gender")
public class GenderRestController {
	@Autowired
	GenderDAO gdDao;
	//ArrayList<Product> listPro = new ArrayList<Product>();
	
	@GetMapping
	public ResponseEntity<List<Gender>> getAll(Model model){
		return ResponseEntity.ok(gdDao.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Gender> getOne(@PathVariable("id") String id){
		Optional<Gender> optional = gdDao.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optional.get());
	}
	
//	@PostMapping
//	public ResponseEntity<Gender> post(@RequestBody Gender gd){
//		if(gdDao.existsById(gd.getIdGender())) {
//			return ResponseEntity.badRequest().build();
//		}
//		gdDao.save(gd);
//		return ResponseEntity.ok(gd);
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<Gender> put(@PathVariable("id") String id , @RequestBody Gender gd){
//		if(!gdDao.existsById(gd.getIdGender())) {
//			return ResponseEntity.notFound().build();
//		}
//		gdDao.save(gd);
//		return ResponseEntity.ok(gd);
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
//		if(!gdDao.existsById(id)) {
//			return ResponseEntity.badRequest().build();
//		}
//		gdDao.deleteById(id);
//		return ResponseEntity.ok().build();
//	}
}
