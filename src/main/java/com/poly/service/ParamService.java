package com.poly.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {
	@Autowired
	HttpServletRequest request;
	
	
	public String getString(String name , String defaulValue) {
		if(request.getParameter(name) != null ) {
			return request.getParameter(name);
		}else {
			return defaulValue;
		}
	}
	
	public int getInt(String name , int defaulValue) {
		if(request.getParameter(name) == null) {
			return defaulValue;
		}else {
			try {
				int in = Integer.parseInt(request.getParameter(name));
				return in;
			} catch (Exception e) {
				return defaulValue;
			}
			
		}
		
	}
	
	public long getLong(String name , int defaulValue) {
		if(request.getParameter(name) == null) {
			return defaulValue;
		}else {
			long in = Long.parseLong(request.getParameter(name));
			return in;
		}
		
	}
	
	public double getDouble(String name , double defaulValue) {
		double db = Double.parseDouble(request.getParameter(name));
		if(name != null) {
			return db;
		}else {
			return defaulValue;
		}
	}
	public boolean getBoolean(String name , boolean defaulValue) {
		boolean bl = Boolean.parseBoolean(request.getParameter(name));
		if(name != null) {
			return bl;
		}else {
			return defaulValue;
		}
	}
//	public Date getDate(String name , String pattern) {
//		return  ;
//	}
	
	@Autowired ServletContext app;
	public File save(MultipartFile file , String path) throws IllegalStateException, IOException {
		if( !file.isEmpty()) {
			String filename = file.getOriginalFilename();
			File file1 = new File(app.getRealPath(path+filename));
			file.transferTo(file1);
			return file1;
		}
		return null ;
	}

}
