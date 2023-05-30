package com.poc.SmartContactManager.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
	

	@Override
	public String uploadImage(int userId,String path, MultipartFile file) throws IOException {
		String name = userId+"_"+file.getOriginalFilename();
		
		String filePath = path + File.separator +name;
		System.out.println(filePath);
		
		File f =  new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException{
		String fullPath = path + File.separator + fileName.toString();
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

}
