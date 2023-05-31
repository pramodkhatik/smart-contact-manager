package com.poc.SmartContactManager.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
	

	@Override
	public String uploadImage(int id,String path, MultipartFile file) throws IOException {
		Random rand = new Random();
		String name = id+"_"+"_"+rand.nextInt(100000)+file.getOriginalFilename();
		
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
