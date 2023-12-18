package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/{id}")
	public ResponseEntity<List<CurrentPlayListDTO>> selectById(@PathVariable String id) {
		List<CurrentPlayListDTO> dtos = cpService.selectById(id);
		return ResponseEntity.ok(dtos);
	}

}
