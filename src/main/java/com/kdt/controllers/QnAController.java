package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.QnaAnswerDTO;
import com.kdt.dto.QnaDTO;
import com.kdt.services.QnaAnswerService;
import com.kdt.services.QnaService;

@RestController
@RequestMapping("/api/qna")
public class QnAController {

	@Autowired
	private QnaService qService;
	
	@Autowired
	private QnaAnswerService qaService;
	
	@PostMapping
	public ResponseEntity<Void> getPost(@RequestBody QnaDTO dto){
		qService.getPost(dto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<QnaDTO>> selectAll(){
		List<QnaDTO> list = qService.selectAll();
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/reply")
	public ResponseEntity<Void> getReplyPost(@RequestBody QnaAnswerDTO dto){
		qaService.insertAnswer(dto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/replylist/{seq}")
	public ResponseEntity<List<QnaAnswerDTO>> getReplyList(@PathVariable Long seq){
		List<QnaAnswerDTO> list = qaService.selectReplies(seq);
		return ResponseEntity.ok(list);
	}
}
