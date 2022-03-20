package com.poly.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.poly.entity.RoleUser;
import com.poly.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
	private Integer idUser;
	private String emailUser;
	private String nameUser;
	private List<Integer> roles ;
	
	public AuthorDTO(User user) {
		this.idUser = user.getIdUser();
		this.emailUser = user.getEmailUser();
		this.nameUser = user.getNameUser();
		List<Integer> list = new ArrayList<Integer>();
		Set<RoleUser> roles = user.getRoleUsers();
		for (RoleUser role : roles) {
			list.add(role.getIdRole());
        }
 		this.roles = list ;
	}
}
