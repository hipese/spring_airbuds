package com.kdt.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.dto.TrackDTO;
import com.kdt.services.TrackService;

@RestController
@RequestMapping("/api/track")
public class TrackController {
	
	
	@Autowired
	TrackService tService;
	
	@PostMapping
    public ResponseEntity<Void> uploadMusic(@RequestParam("file") MultipartFile[] files, 
    										@RequestParam("duration") String[] durations) throws Exception {
		tService.insert(files,durations);
        return ResponseEntity.ok().build();
    }
	
	@GetMapping
	public ResponseEntity<List<TrackDTO>> selectAll(){
		List<TrackDTO> dtos=tService.selectAll();
		return ResponseEntity.ok(dtos);
	}
	
	@DeleteMapping("/{track_id}")
	public ResponseEntity<Void> deleteByIdTrack(@PathVariable String track_id){
		
		System.out.println("이거 뭐로 받음: "+track_id);
		tService.deleteByIdTrack(track_id);
		return ResponseEntity.ok().build();
	}
	

}
