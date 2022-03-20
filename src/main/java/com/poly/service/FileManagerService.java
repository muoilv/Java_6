package com.poly.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileManagerService {
	@Autowired
	ServletContext app;

	private Path getPath(String folder, String filename) {
		File dir = Paths.get(app.getRealPath("/webapp/image/"),folder).toFile();
		if(!dir.exists()) {
			dir.mkdirs();
		}
		return Paths.get(dir.getAbsolutePath(), filename);
	}
	
	public byte[] read(String folder, String filename) {
		Path path = this.getPath(folder, filename);
		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String save(MultipartFile file) {
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
	
	public void delete(String folder, String filename) {
		Path path = this.getPath(folder, filename);
		path.toFile().delete();
	}
	
	public List<String> list(String folder) {
		List<String> filenames = new ArrayList<String>();
		File dir = Paths.get(app.getRealPath("/webapp/image/"), folder).toFile();
		if(dir.exists()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				filenames.add(file.getName());
			}
		}
		return filenames;
	}

}
