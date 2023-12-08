package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.TrackDTO;
import com.kdt.services.TrackService;
import com.kdt.services.TrackTagService;

@RestController
@RequestMapping("/api/trackTag")
public class TrackTagController {
	
	@Autowired
	private TrackService tServ;
	
	@Autowired
	private TrackTagService ttServ;	
	
	@GetMapping("/romance")
	public ResponseEntity<List<TrackDTO>> selectRomance() {
		List<Long> list=ttServ.selectRomance();
		List<TrackDTO> tlist = tServ.selectOne(list);
		return ResponseEntity.ok(tlist);
	}
}
