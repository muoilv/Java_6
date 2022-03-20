package com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.StatusObjectDAO;
import com.poly.entity.StatusObject;
import com.poly.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService{
@Autowired
StatusObjectDAO sdao;

@Override
public StatusObject findByIdStatus(String id) {
	
	if(id == null || id.equals("")) {
		return null;
	}
	else { 
		StatusObject stt = sdao.findByIdStatus(id);
		if(stt != null) {
			return stt;
		}else {
			return null;
		}
	}
}

}
