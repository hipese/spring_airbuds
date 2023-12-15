package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.AlbumTagListDTO;
import com.kdt.services.AlbumTagListService;

@RestController
@RequestMapping("/api/albumTagList")
public class AlbumTagListController {
	
	
	@Autowired
	private AlbumTagListService ATLservice;
	
	@GetMapping
	public ResponseEntity<List<AlbumTagListDTO>> selectAll(){
		
		List<AlbumTagListDTO> dtos=ATLservice.selectAll();
		return ResponseEntity.ok(dtos);
	}
}
