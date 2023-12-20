package com.kdt.controllers;

import java.time.Instant;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.MemberDTO;
import com.kdt.dto.ReportAnswerDTO;
import com.kdt.dto.ReportDTO;
import com.kdt.dto.SanctionViewDTO;
import com.kdt.services.MemberService;
import com.kdt.services.ReportAnswerService;
import com.kdt.services.ReportService;
import com.kdt.services.SanctionService;
import com.kdt.services.TrackService;

@RestController
@RequestMapping("/api/report")
public class ReportController {

	@Autowired
	private ReportService rService;

	@Autowired
	private ReportAnswerService raService;

	@Autowired
	private MemberService mService;

	@Autowired
	private TrackService tService;

	@Autowired
	private SanctionService svService;

	// 신고 insert
	@PostMapping
	public ResponseEntity<Void> insertReport(@RequestParam("reason") String title,
			@RequestParam("trackId") String trackId,
			@RequestParam("ReportWriter") String writer,
			@RequestParam("ReportSubject") String subject,
			@RequestParam("ReportCategory") String category) throws Exception{
		ReportDTO dto = new ReportDTO();
		dto.setReportSeq(0L);
		dto.setReportTitle(title);
		dto.setReportContents(trackId+" 번 노래 신고 사유: "+title+" - "+category);
		dto.setReportWriter(writer);
		dto.setReportSubject(subject);
		dto.setReportCategory(category);
		dto.setReportWriteDate(Instant.now());
		dto.setReportAnswerState(0L);
		
		rService.insertReport(dto);

		return ResponseEntity.ok().build();
	}	

	// 신고 리스트
	@GetMapping
	public ResponseEntity <List<ReportDTO>> selectAll() {
		List<ReportDTO> list = rService.selectAll();
		return ResponseEntity.ok(list);
	}

	// 답변 작성
	@PostMapping("/answer")
	public ResponseEntity<Void> getReplyPost(@RequestBody ReportAnswerDTO dto){
		Instant time = Instant.now();
		dto.setReportAnswerWriteDate(time);
		raService.insertAnswer(dto);
		return ResponseEntity.ok().build();
	}

	// 답변 리스트 
	@GetMapping("/answerlist/{seq}")
	public ResponseEntity<List<ReportAnswerDTO>> getReplyList(@PathVariable Long seq){
		List<ReportAnswerDTO> list = raService.selectReplies(seq);
		return ResponseEntity.ok(list);
	}

	// Detail 로
	@GetMapping("/contents/{seq}")
	public ResponseEntity<ReportDTO> getContents(@PathVariable Long seq) throws Exception{
		ReportDTO dto = rService.getContents(seq);
		return ResponseEntity.ok(dto);
	}

	// 신고 삭제
	@DeleteMapping("/delete/{seq}")
	public ResponseEntity<String> deletePost(@PathVariable Long seq) throws Exception{
		rService.deletePost(seq);	
		return ResponseEntity.ok().build();
	}

	// 이메일로 안내메시지를 전송하는
	@PostMapping("/notice")
	public ResponseEntity<String> notice(@RequestParam String id, @RequestParam String contents) {
		MemberDTO dto = mService.findEmail(id);
		rService.sendEmail(dto.getEmail(), contents);
		return ResponseEntity.ok("success");
	}

	// 답변 완료
	@PutMapping("/state/{reportSeq}")
	public ResponseEntity<String> changeState(@PathVariable Long reportSeq) {
		rService.changeState(reportSeq);
		return ResponseEntity.ok("success");
	}

	// 모든 Track 리스트 및 제재
	@GetMapping("/trackList")
	public ResponseEntity <List<SanctionViewDTO>> selectAllTrack() {
		List<SanctionViewDTO> list = svService.selectAll();
		return ResponseEntity.ok(list);
	}

	// 제재
	@PutMapping("/sanction/state")
	public ResponseEntity<String> changeSanctionState(@RequestParam Long trackId, @RequestParam String reason) {
		svService.changeSanctionState(trackId,reason);
		return ResponseEntity.ok("success");
	}

	// 커버이미지 디폴트값으로
	@PutMapping("/sanction/image/{trackId}")
	public ResponseEntity<String> changeImage(@PathVariable Long trackId) {
		svService.changeImage(trackId);
		return ResponseEntity.ok("success");
	}

	// 모든 제재된 리스트
	@GetMapping("/sanctionList")
	public ResponseEntity <List<SanctionViewDTO>> selectAllSanction() {
		List<SanctionViewDTO> list = svService.selectSanctionAll();
		return ResponseEntity.ok(list);
	}

	// 해제
	@PutMapping("/sanction/release/{trackId}")
	public ResponseEntity<String> releaseSanction(@PathVariable Long trackId) {
		svService.releaseSanction(trackId);
		return ResponseEntity.ok("success");
	}

}
