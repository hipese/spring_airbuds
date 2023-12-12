package com.kdt.controllers;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<Void> getPost(@RequestParam(value = "files", required = false) MultipartFile[] files,
										@RequestParam("qnaContents") String contents,
										@RequestParam("qnaWriter") String writer,
										@RequestParam("qnaTitle") String title,
										@RequestParam("qnaWriteDate") Instant write_date ,
										@RequestParam("qnaAnswerState") Long state,
										@RequestParam("qnaPublic") Long isPublic,
										@RequestParam("qnaCategory") String category) throws Exception{
		QnaDTO dto = new QnaDTO();
		dto.setQnaSeq(0L);
		dto.setQnaTitle(title);
		dto.setQnaContents(contents);
		dto.setQnaWriter(writer);
		dto.setQnaWriteDate(write_date);
		dto.setQnaAnswerState(state);
		dto.setQnaPublic(isPublic);
		dto.setQnaCategory(category);
		
		qService.getPost(dto, files);
		
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
	
	@GetMapping("/contents/{seq}")
	public ResponseEntity<QnaDTO> getContents(@PathVariable Long seq) throws Exception{
		QnaDTO dto = qService.getContents(seq);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/delete/{seq}")
	public ResponseEntity<String> deletePost(@PathVariable Long seq) throws Exception{
		qService.deletePost(seq);		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/reply/delete/{seq}")
	public ResponseEntity<String> deleteAnswer(@PathVariable Long seq){
		qaService.deleteAnswer(seq);
		return ResponseEntity.ok().build();
	}
}
