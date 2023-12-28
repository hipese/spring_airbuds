package com.kdt.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.TrackTagDTO;

import com.kdt.services.TrackTagService;



@RestController
@RequestMapping("/api/trackTag")
public class TrackTagController {

	@Autowired
	private TrackTagService ttServ;
	
	private static final Logger logger=LoggerFactory.getLogger(TrackTagController.class);

	@GetMapping
	public ResponseEntity<List<TrackTagDTO>> selectTag(@RequestParam(name = "tag") Long tag) {
		List<TrackTagDTO> list = ttServ.selectTag(tag);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/selectTagById/{id}")
	public ResponseEntity<List<TrackTagDTO>> selectTagById(@PathVariable Long id){
		
		logger.debug("여기로 오냐?");
		List<TrackTagDTO> list=ttServ.selectTagById(id);
		return ResponseEntity.ok(list);
	}
}
