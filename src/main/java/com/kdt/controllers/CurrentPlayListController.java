package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.CurrentPlayListDTO;
import com.kdt.services.CurrentPlayListService;

@RestController
@RequestMapping("/api/cplist")
public class CurrentPlayListController {

	@Autowired
	CurrentPlayListService cpService;

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody CurrentPlayListDTO dto) throws Exception {
		System.out.println(dto.getTrackId());
		cpService.insert(dto);
		return ResponseEntity.ok().build();
	}

}
