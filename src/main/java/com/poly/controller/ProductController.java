package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.service.CategoryService;
import com.poly.service.ProductService;
import com.poly.service.SessionService;

@Controller
public class ProductController {

	@Autowired
	ProductService proService;
	@Autowired
	CategoryService cateService;
	@Autowired
	SessionService session;
	
	
	@RequestMapping("/product/index")
	public String list(Model model, @RequestParam("page") Optional<Integer> page) {
		Page<Product> pagePro = proService.findAll(page);
		model.addAttribute("pagePro",pagePro );
		List<Category> listCate = cateService.findAll();
		model.addAttribute("listCate",listCate );
		session.remove("keySearchHome");
		return"product/list";
	}
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product proDetail = proService.findById(id);
		model.addAttribute("proDetail", proDetail);
		List<Product> listProByCate = proService.findAllByCategory(proDetail.getCategory());
		model.addAttribute("listProByCate", listProByCate);
		session.remove("keySearchHome");
		return"product/detail";
	}
	
	@RequestMapping("/product/search")
	public String search(Model model,
			@RequestParam("keySearchHome") Optional<String> kw,
			@RequestParam("page") Optional<Integer> page) {
		String kwords = kw.orElse(session.get("keySearchHome"));
		session.set("keySearchHome", kwords);
		
		Page<Product> pagePro = proService.findAllByNameProduct(kw.orElse(""), page);
		model.addAttribute("pagePro",pagePro );
		
		List<Category> listCate = cateService.findAll();
		model.addAttribute("listCate",listCate );
		
		return"product/list";
	}
	
}
