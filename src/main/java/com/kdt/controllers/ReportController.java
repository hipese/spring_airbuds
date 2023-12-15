package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.ReportDTO;
import com.kdt.services.ReportService;

@RestController
@RequestMapping("/api/report")
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@GetMapping
	public ResponseEntity <List<ReportDTO>> selectAll() {
		List<ReportDTO> list = reportService.selectAll();
		return ResponseEntity.ok(list);
	}
	
	// 이메일로 안내메시지를 전송하는
	@PostMapping("/notice")
	public ResponseEntity<String> register(@RequestParam String email, @RequestParam String contents) {
		reportService.sendEmail(email, contents);
		return ResponseEntity.ok("success");
	}
}
