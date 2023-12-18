package com.kdt.controllers;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.ReportAnswerDTO;
import com.kdt.dto.ReportDTO;
import com.kdt.dto.ReportDTO;
import com.kdt.services.ReportAnswerService;
import com.kdt.services.ReportService;

@RestController
@RequestMapping("/api/report")
public class ReportController {

	@Autowired
	private ReportService rService;

	@Autowired
	private ReportAnswerService raService;

	// 신고 insert
	@PostMapping
	public ResponseEntity<Void> getPost(@RequestParam("ReportTitle") String title,
			@RequestParam("ReportContents") String contents,
			@RequestParam("ReportWriter") String writer,
			@RequestParam("ReportSubject") String subject,
			@RequestParam("ReportCategory") String category,
			@RequestParam("ReportWriteDate") Instant write_date ,
			@RequestParam("ReportAnswerState") Long state) throws Exception{
		ReportDTO dto = new ReportDTO();
		dto.setReportSeq(0L);
		dto.setReportTitle(title);
		dto.setReportContents(contents);
		dto.setReportWriter(writer);
		dto.setReportSubject(subject);
		dto.setReportCategory(category);
		dto.setReportWriteDate(write_date);
		dto.setReportAnswerState(state);

		return ResponseEntity.ok().build();
	}	

	// 신고 리스트
	@GetMapping
	public ResponseEntity <List<ReportDTO>> selectAll() {
		List<ReportDTO> list = rService.selectAll();
		return ResponseEntity.ok(list);
	}

	@PostMapping("/reply")
	public ResponseEntity<Void> getReplyPost(@RequestBody ReportAnswerDTO dto){
		Instant time = Instant.now();
		dto.setReportAnswerWriteDate(time);
		raService.insertAnswer(dto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/replylist/{seq}")
	public ResponseEntity<List<ReportAnswerDTO>> getReplyList(@PathVariable Long seq){
		List<ReportAnswerDTO> list = raService.selectReplies(seq);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/contents/{seq}")
	public ResponseEntity<ReportDTO> getContents(@PathVariable Long seq) throws Exception{
		ReportDTO dto = rService.getContents(seq);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/delete/{seq}")
	public ResponseEntity<String> deletePost(@PathVariable Long seq) throws Exception{
		rService.deletePost(seq);	
		return ResponseEntity.ok().build();
	}

	// 이메일로 안내메시지를 전송하는
	@PostMapping("/notice")
	public ResponseEntity<String> register(@RequestParam String email, @RequestParam String contents) {
		rService.sendEmail(email, contents);
		return ResponseEntity.ok("success");
	}
}
