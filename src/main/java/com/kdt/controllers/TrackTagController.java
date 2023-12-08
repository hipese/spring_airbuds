package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.MusicTagDTO;
import com.kdt.dto.TrackTagDTO;
import com.kdt.services.TrackService;
import com.kdt.services.TrackTagService;

@RestController
@RequestMapping("/api/trackTag")
public class TrackTagController {
	
	@Autowired
	private TrackService tServ;
	
	@Autowired
	private TrackTagService ttServ;	
	
//	@GetMapping
//	public ResponseEntity<List<TrackTagDTO>> selectTag(@PathVariable MusicTagDTO tagName) {
//		List<TrackTagDTO> list=ttServ.selectTag(tagName);
//		return ResponseEntity.ok(list);
//	}
}
