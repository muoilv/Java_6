package com.poly.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.dao.BillDAO;
import com.poly.dao.GenderDAO;
import com.poly.dao.UserDAO;
import com.poly.entity.Bill;
import com.poly.entity.User;
import com.poly.service.SessionService;
import com.poly.service.UserService;

@Controller
public class SecurityController {

	@Autowired
	UserService userSv;
	@Autowired
	GenderDAO genderDAO;
	@Autowired
	BillDAO billDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	SessionService session;
	@Autowired
	ServletContext app;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập!");
		return "/security/login";

	}

	@RequestMapping("/security/login/success")
	public String loginSucces(Model model, HttpServletRequest request) {
		model.addAttribute("message", "Đăng nhập thành công !");
		User us = userSv.findByEmail(request.getRemoteUser());
		session.set("user", us);
		List<Bill> lstBill = billDAO.findByUser(us);
		if(!lstBill.isEmpty()) {
			session.set("displayAllBill",true);
		}
		return "/security/login";

	}

	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập !");
		return "/security/login";

	}

	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Không có quyền truy xuất");
		return "/security/login";
	}

	@RequestMapping("/security/logoff/sucsess")
	public String logoff(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất ");
		session.remove("user");
		session.set("displayAllBill", false);
		return "/security/login";
	}

	@RequestMapping("/security/register")
	public String getRegister(Model model, @ModelAttribute("user") User user) {
		return "/security/register";
	}

	@PostMapping("/security/postregister")
	public String postRegister(Model model, 
			@Valid @ModelAttribute("user") User user, Errors errors) throws IllegalStateException, IOException {
		
			if (errors.hasErrors()) {
				model.addAttribute("message", "Vui lòng sửa các lỗi");
			} else {
				if (userDAO.findByEmailUser(user.getEmailUser()) == null) {

					
					user.setPasswordUser(passwordEncoder.encode(user.getPasswordUser()));
					user.setGender(genderDAO.findAllByIdGenderLike(user.getGender().getIdGender()));
					userDAO.save(user);
					model.addAttribute("message", "Tạo tài khoản thành công");
					System.out.println(user);

				} else {
					model.addAttribute("message", "Email đã tồn tại");
				}

			}
		
		return "/security/register";
	}
}
