package com.poly.rest.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poly.config.JwtUtility;
import com.poly.dao.UserDAO;
import com.poly.dto.JwtResponse;
import com.poly.dto.LoginDTO;
import com.poly.entity.RoleUser;
import com.poly.entity.User;
import com.poly.service.impl.UserDetailsServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class LoginRestController {
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private JwtUtility jwtUtility;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	
	@GetMapping("/authenticate")
	public String home() {
		return "Come on Baby";
	}
	
	@PostMapping("/api/login")
	public JwtResponse<User> login(@RequestBody LoginDTO user) throws Exception {
		JwtResponse<User> jwtResponse =new JwtResponse<User>();
		User us = null;
		us = userDAO.findByEmailUser(user.getEmailUser());
		if(us != null) {
			
				try {
					authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailUser(), user.getPasswordUser()));
					final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getEmailUser());
					final String token = jwtUtility.generateToken(userDetails);
					
					us.setPasswordUser("");
					
					boolean checkAdmin = false;
					Set<RoleUser> roles = us.getRoleUsers();
			        for (RoleUser role : roles) {
			            if(role.getNameRole().equals("ROLE_ADMIN")) {
			            	checkAdmin = true;
			            }
			        }
					if(checkAdmin== true){
						jwtResponse.setStatus(1);
						jwtResponse.setMessage("Đăng nhập thành công");
						jwtResponse.setJwtToken(token);
						jwtResponse.setData(us);
					}else {
						jwtResponse.setStatus(0);
						jwtResponse.setMessage("Bạn không có quyền");
					}
					
				} catch (BadCredentialsException e) {
					jwtResponse.setStatus(-1);
					jwtResponse.setMessage("Không đúng email mật khẩu");
					throw new Exception("Invalid", e);
					
				}
			
		}else {
			jwtResponse.setStatus(-2);
			jwtResponse.setMessage("Không đúng email");
		}
		
		return jwtResponse;
	}
	
	
	
	
}
