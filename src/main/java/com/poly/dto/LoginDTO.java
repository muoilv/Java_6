package com.poly.dto;

import com.poly.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
	
	private String emailUser;
	private String passwordUser;
	private Boolean rememberMe;
	
	public LoginDTO(User user) {
		this.emailUser = user.getEmailUser();
		this.passwordUser = user.getPasswordUser();
	}
}
