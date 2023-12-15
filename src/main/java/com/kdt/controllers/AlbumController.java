package com.kdt.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.services.AlbumService;
import com.kdt.services.TrackService;

@RestController
@RequestMapping("/api/album")
public class AlbumController {

	@Autowired
	AlbumService aService;
	
	@Autowired
	TrackService tService;
	
	@PostMapping
	public ResponseEntity<Void> insertAlbum(@RequestParam("file") MultipartFile[] files, 
											@RequestParam("name") String[] name, 
											@RequestParam("duration") String[] durations,
											@RequestParam("image_path") String[] image_path,
											@RequestParam("releaseDate") String releaseDate,
											@RequestParam(value = "titleImage", required = false) MultipartFile titleImage,
											@RequestParam("writer") String[] writers,
											@RequestParam("albumselectTag" ) Long[] albumselectTag,
											@RequestParam(value="order", required = false) String[] order,
											@RequestParam("albumTitle") String albumTitle,
											@RequestParam("login") String loginId,
											@RequestParam MultiValueMap<String, String> trackTags)throws Exception{
	    
//		aService.insertAlbum(files,name,durations,image_path,releaseDate,titleImage,writers,albumselectTag,order,albumTitle,loginId,trackTags);
		return ResponseEntity.ok().build();
	}
	
	
}
