package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.PlaylistDTO;
import com.kdt.services.PlaylistService;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

	@Autowired
	private PlaylistService plServ;
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody PlaylistDTO dto) {
		plServ.insert(dto);
		return ResponseEntity.ok().build();
	}
}
