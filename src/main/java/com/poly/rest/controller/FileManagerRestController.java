package com.poly.rest.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.poly.service.FileManagerService;

@CrossOrigin("*")
@RestController
public class FileManagerRestController {

	@Autowired
	FileManagerService fileService;
	@Autowired
	ServletContext app;
	
	@GetMapping("/api/upload/{folder}/{file}")
	public byte[] download(@PathVariable("folder") String folder ,@PathVariable("file") String file){
		return fileService.read(folder,file);
	}
	@PostMapping(value = "/api/upload")
	public String upload(@RequestParam MultipartFile file){
		File fileee = new File(app.getRealPath("/webapp/image/"));
		if(!fileee.exists()) {
			fileee.mkdirs();
		}
		if(!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			File newFile = new File(app.getRealPath("/webapp/image/" + fileName));
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				return null;
			}
			return "/webapp/image/" + fileName;
		}
		return null;
	}
	
	@DeleteMapping("/api/upload/{folder}/{file}")
	public void delete(@PathVariable("folder") String folder, @PathVariable("file") String file) {
		fileService.delete(folder, file);
	}
	
	@GetMapping("/api/upload/{folder}")
	public List<String> list(@PathVariable("folder") String folder ){
		return fileService.list(folder);
	}
	
}
