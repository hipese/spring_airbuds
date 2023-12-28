package com.kdt.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.ReplyLikeCountDTO;
import com.kdt.dto.TrackReplyDTO;
import com.kdt.services.TrackReplyService;

@RestController
@RequestMapping("/api/reply")
public class TrackReplyController {

	@Autowired
	private TrackReplyService trService;
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody TrackReplyDTO dto){
		trService.insert(dto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{seq}")
	public ResponseEntity<List<ReplyLikeCountDTO>> selectByTrackId(@PathVariable Long seq){
		List<ReplyLikeCountDTO> list = trService.selectByTrackId(seq);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/writer/{writer}")
	public ResponseEntity<List<TrackReplyDTO>> selectByTrackId(@PathVariable String writer){
		List<TrackReplyDTO> list = trService.selectByWriter(writer);
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("/{seq}")
	public ResponseEntity<String> deleteReply(@PathVariable Long seq, Principal principal){
		trService.deleteReply(seq,principal);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{seq}")
	public ResponseEntity<String> updateReply(@PathVariable Long seq, @RequestBody TrackReplyDTO dto){
		trService.updateReply(dto);
		return ResponseEntity.ok().build();
	}
}
