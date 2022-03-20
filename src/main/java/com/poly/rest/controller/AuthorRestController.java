package com.poly.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.RoleUserDAO;
import com.poly.dao.UserDAO;
import com.poly.dto.AuthorDTO;
import com.poly.dto.JwtResponse;
import com.poly.dto.LoginDTO;
import com.poly.entity.RoleUser;
import com.poly.entity.User;

@CrossOrigin(origins = "*")
@RestController
public class AuthorRestController {

	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RoleUserDAO roleUserDAO;

	@GetMapping("/api/authoruser")
	public ResponseEntity<List<AuthorDTO>> author() {
		List<User> listUser = userDAO.findAll();
		List<AuthorDTO> listAuthor = new ArrayList<AuthorDTO>();
		
		for (User user : listUser) {
			AuthorDTO authorDTO = new AuthorDTO();
			authorDTO.setIdUser(user.getIdUser());
			authorDTO.setEmailUser(user.getEmailUser());
			authorDTO.setNameUser(user.getNameUser());
			List<Integer> list = new ArrayList<Integer>();
			Set<RoleUser> roles = user.getRoleUsers();
			for (RoleUser role : roles) {
				list.add(role.getIdRole());
	        }
			authorDTO.setRoles(list);
			listAuthor.add(authorDTO);
		}
	
		return ResponseEntity.ok(listAuthor);
	}
	@RequestMapping(value = "/api/authoruser" , method = RequestMethod.DELETE)
	public Integer delete(@RequestBody AuthorDTO author) throws Exception {
		//userDAO.delete(author.getIdUser(), author.getRoles())
		return 1;
	}
}
