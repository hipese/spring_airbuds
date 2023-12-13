package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.services.AlbumService;

@RestController
@RequestMapping("/api/album")
public class AlbumController {

	@Autowired
	AlbumService aService;
	
	@PostMapping
	public ResponseEntity<Void> insertAlbum(@RequestParam("file") MultipartFile[] files, 
											@RequestParam("name") String[] name, 
											@RequestParam("duration") String[] durations,
											@RequestParam("image_path") String[] image_path,
											@RequestParam("releaseDate") String releaseDate,
											@RequestParam(value = "imagefile", required = false) MultipartFile imagefile,
											@RequestParam("writer") String[] writer,
											@RequestParam(value="tag", required = false) String[] tag,
											@RequestParam("albumTitle") String albumTitle,
											@RequestParam("login") String loginId)throws Exception{
		

		System.out.println(albumTitle);
		System.out.println(releaseDate);
		System.out.println("로그인 값:"+loginId);
		
		aService.insert(files,name,durations,image_path,releaseDate,imagefile,writer);
		return ResponseEntity.ok().build();
	}
}
