package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.TrackReplyLikeDTO;
import com.kdt.services.TrackReplyLikeService;

@RestController
@RequestMapping("/api/tllike")
public class TrackReplyLikeController {

	@Autowired
	private TrackReplyLikeService trService;
	
	@PostMapping
	public ResponseEntity<Long> addFavorite(TrackReplyLikeDTO dto) {
		if (dto.getId().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		System.out.println("userid" + dto.getId());
		System.out.println("trackid" + dto.getReplySeq());
		Long seq = trService.insert(dto);

		return ResponseEntity.ok(seq);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<TrackReplyLikeDTO>> getFavorite(@PathVariable String id) {
		List<TrackReplyLikeDTO> list = trService.selectAllById(id);
		return ResponseEntity.ok(list);
	}

	@PostMapping("/delete")
	public ResponseEntity<String> deleteFavorite(TrackReplyLikeDTO dto) {
		System.out.println(dto.getId());
		trService.deleteFavorite(dto);
		return ResponseEntity.ok(null);
	}
}
