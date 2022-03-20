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
import com.poly.dao.ProductDAO;
import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductRestController {

	@Autowired
	ProductService proService;
	@Autowired
	ProductDAO proDAO;
	@Autowired
	CategoryDAO cateDAO;
	//ArrayList<Product> listPro = new ArrayList<Product>();
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll(Model model, @RequestParam("nameProduct") String key){
		return ResponseEntity.ok(proService.findAllByNameProduct(key));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getOne(@PathVariable("id") Integer id){
		Optional<Product> optional = proDAO.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optional.get());
	}
	@GetMapping("/findbycate/{id}")
	public ResponseEntity<List<Product>> getByCate(@PathVariable("id") Integer id){
		Optional<Category> cate = cateDAO.findById(id);
		return ResponseEntity.ok(proDAO.findAllByCategory(cate.orElse(new Category())));
	}
	
	@PostMapping
	public ResponseEntity<Product> post(@RequestBody Product pro){
		if(proDAO.existsById(pro.getIdProduct())) {
			return ResponseEntity.badRequest().build();
		}
		proDAO.save(pro);
		return ResponseEntity.ok(pro);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> put(@PathVariable("id") Integer id , @RequestBody Product pro){
		if(!proDAO.existsById(pro.getIdProduct())) {
			return ResponseEntity.notFound().build();
		}
		proDAO.save(pro);
		return ResponseEntity.ok(pro);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		if(!proDAO.existsById(id)) {
			return ResponseEntity.badRequest().build();
		}
		proDAO.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
