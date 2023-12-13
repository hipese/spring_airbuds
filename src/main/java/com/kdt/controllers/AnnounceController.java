package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.AnnounceBoardDTO;
import com.kdt.services.AnnounceService;

@RestController
@RequestMapping("/api/announce")
public class AnnounceController {

	@Autowired
	private AnnounceService aService;
	
	@PostMapping
	public ResponseEntity<String> postAnnounce(@RequestBody AnnounceBoardDTO dto){
		dto.setAnnounceViewCount(0L);
		aService.insertAnnounce(dto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<AnnounceBoardDTO>> getAnnounceList(){
		List<AnnounceBoardDTO> list = aService.selectAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{seq}")
	public ResponseEntity<AnnounceBoardDTO> getAnnounceDetail(@PathVariable Long seq){
		AnnounceBoardDTO dto = aService.getAnnounceDetail(seq);
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{seq}")
	public ResponseEntity<String> addViewCount(@PathVariable Long seq){
		aService.addViewCount(seq);
		return ResponseEntity.ok().build();
	}
}
