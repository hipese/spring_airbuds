package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.MusicTagDTO;
import com.kdt.services.MusicTagService;

@RestController
@RequestMapping("/api/MusicTag")
public class MusicTagController {

	@Autowired
	MusicTagService mTagService;
	
	@GetMapping
	public ResponseEntity<List<MusicTagDTO>> selectAll(){
		
		List<MusicTagDTO> dtos=mTagService.selectAll();
		return ResponseEntity.ok(dtos);
	}
}
