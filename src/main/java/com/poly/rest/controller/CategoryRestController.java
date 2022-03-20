package com.poly.rest.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.CategoryDAO;
import com.poly.entity.Category;
import com.poly.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

	@Autowired
	CategoryDAO cateDao;
	@Autowired
	CategoryService cateService;
	//ArrayList<Product> listPro = new ArrayList<Product>();
	
	@GetMapping
	public ResponseEntity<List<Category>> getAll(Model model, @RequestParam("nameCategory") String key){
		return ResponseEntity.ok(cateService.findAllByNameCategoryLike(key));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable("id") Integer id){
		Optional<Category> optional = cateDao.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping
	public ResponseEntity<Category> post(@RequestBody Category cate){
		if(cateDao.existsById(cate.getIdCategory())) {
			return ResponseEntity.badRequest().build();
		}
		cateDao.save(cate);
		return ResponseEntity.ok(cate);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> put(@PathVariable("id") Integer id , @RequestBody Category cate){
		if(!cateDao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cateDao.save(cate);
		return ResponseEntity.ok(cate);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		if(!cateDao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cateDao.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
