package com.kdt.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.dto.AlbumDTO;
import com.kdt.services.AlbumService;
import com.kdt.services.TrackService;

import jakarta.servlet.http.HttpSession;

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
											@RequestParam(value = "titleImage", required = false) MultipartFile titleImage,
											@RequestParam("writer") String[] writers,
											@RequestParam("albumselectTag" ) Long[] albumselectTag,
											@RequestParam(value="order", required = false) Long[] order,
											@RequestParam("albumTitle") String albumTitle,
											@RequestParam("login") String loginId,
											@RequestParam MultiValueMap<String, String> trackTags)throws Exception{
	    
		aService.insertAlbum(files,name,durations,image_path,releaseDate,titleImage,writers,albumselectTag,order,albumTitle,loginId,trackTags);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/findByLogin")
	public ResponseEntity<List<AlbumDTO>> selectByLoginId(Principal principal){
		
		List<AlbumDTO> dto=aService.selectByLoginId(principal);
		return ResponseEntity.ok(dto);
	}
	
	
	@PostMapping("/updateAlbum")
	public ResponseEntity<AlbumDTO> updateAlbum(@RequestParam(value="file", required = false) MultipartFile[] files, 
												@RequestParam(value = "name", required = false) String[] name,
												@RequestParam(value = "duration", required = false) String[] durations,
												@RequestParam(value="image_path", required = false) String[] image_path,
												@RequestParam(value = "writer", required = false) String[] writers,
												@RequestParam(value = "titleImage", required = false) MultipartFile titleImage,
												@RequestParam("albumselectTag") Long[] albumselectTag,
												@RequestParam("albumTitle") String albumTitle,
												@RequestParam("albumsWriters") String [] albumsWriters,
												@RequestParam("Tracktitles") String [] Tracktitles,
												@RequestParam(value="prevImage" ,required = false) String prevImage,
												@RequestParam(value="deleteTrack" ,required = false) Long[] deleteTrack,
												@RequestParam MultiValueMap<String, String> trackTags,
												@RequestParam(value="albumId" ,required = false) Long albumId) throws Exception{
				

		System.out.println("함수 실행전!!!");
		AlbumDTO dto=aService.updateAlbum(files,name,durations,image_path,writers,titleImage,
				albumselectTag,albumTitle,albumsWriters,Tracktitles,prevImage,deleteTrack,trackTags,albumId);
		System.out.println("리턴후 오냐?");
//		return ResponseEntity.ok(null);
		return ResponseEntity.ok(dto);
	}
	
}
