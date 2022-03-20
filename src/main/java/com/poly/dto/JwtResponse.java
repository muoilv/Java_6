package com.poly.dto;

import java.util.Set;

import com.poly.entity.RoleUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtResponse<T> {
	private int status;
	private String message;
	private String jwtToken;

	private T data;
	
	public JwtResponse(int status){
		this.status = status;
	}
	public JwtResponse(int status, String message){
		this(status);
		this.message = message;
	}
}
