package com.kdt.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.dto.AlbumDTO;
import com.kdt.mappers.AlbumMapper;
import com.kdt.repositories.AlbumRepository;

@Service
public class AlbumService {

	@Autowired 
	AlbumRepository aRepo;
	
	@Autowired
	AlbumMapper aMapper;
	
	
	public void insert(MultipartFile[] files, 
	 		   		   String[] name,
	 		   		   String[] durations, 
	 		   		   String[] image_path,
	 		   		   String releaseDate,
	 		   		   MultipartFile imagefile, 
	 		   		   String[] writer) {
		
		File imagePath = new File("c:/tracks/image");
		
		if (!imagePath.exists()) {
			imagePath.mkdir();
		}
		
		AlbumDTO adto=new AlbumDTO();
		adto.setTitle(releaseDate);
		
		
	}
	
}
