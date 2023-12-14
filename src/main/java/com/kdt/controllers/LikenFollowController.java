package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.MusicLikeDTO;
import com.kdt.services.LikeService;

@RestController
@RequestMapping("/api/like")
public class LikenFollowController {

	@Autowired
	private LikeService lService;
	
	@PostMapping
	public ResponseEntity<String> addFavorite(MusicLikeDTO dto){
		System.out.println(dto.getTrackId());
		if(dto.getId().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		System.out.println(dto.getId());
		lService.insertFavorite(dto);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<MusicLikeDTO>> getFavorite(@PathVariable String id){
		List<MusicLikeDTO> list = lService.selectAllById(id);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteFavorite(MusicLikeDTO dto){
		System.out.println(dto.getId());
		lService.deleteFavorite(dto);
		return ResponseEntity.ok(null);
	}
}
