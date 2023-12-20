package com.kdt.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.DailyStreamingDTO;
import com.kdt.dto.DailyVisitDTO;
import com.kdt.dto.MemberAgeViewDTO;
import com.kdt.services.DashboardService;
import com.kdt.services.MemberService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	@Autowired
	private MemberService mService;
	
	@Autowired
	private DashboardService dService;
	
	@GetMapping("/report")
	public ResponseEntity<List<HashMap<String,Object>>> getReport(){
		List<Map<String,Object>> list = dService.getList();
		List<HashMap<String, Object>> resultList = new ArrayList<>();

        for (Map<String, Object> originalMap : list) {
            HashMap<String, Object> resultMap = new HashMap<>(originalMap);
            resultList.add(resultMap);
        }
		
		return ResponseEntity.ok(resultList);
	}
	
	@GetMapping("/music")
	public ResponseEntity<List<HashMap<String,Object>>> getMusic(){
		List<Map<String,Object>> list = dService.getMusicData();
		List<HashMap<String, Object>> resultList = new ArrayList<>();

        for (Map<String, Object> originalMap : list) {
            HashMap<String, Object> resultMap = new HashMap<>(originalMap);
            resultList.add(resultMap);
        }
		
		return ResponseEntity.ok(resultList);
	}
	
	@GetMapping("/member")
	public ResponseEntity<List<MemberAgeViewDTO>> getMember(){
		List<MemberAgeViewDTO> list = dService.getMemberAge();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/reportCount")
	public ResponseEntity<Long> getReportCount(){
		Long count = dService.getReportCount();
		return ResponseEntity.ok(count);
	}
	
	@PutMapping("/visit")
	public ResponseEntity<String> insertVisit(DailyVisitDTO dto){
		dService.insertVisit(dto);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/visitorCount")
	public ResponseEntity<Long> getVisitorCount(){
		Long count = dService.getDailtCount();
		return ResponseEntity.ok(count);
	}
	
	@PutMapping("/addStream")
	public ResponseEntity<String> insertStreamCount(DailyStreamingDTO dto){
		dService.insertStreamCount(dto);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/streamCount")
	public ResponseEntity<Long> getStreamCount(){
		Long count = dService.getStreamCount();
		return ResponseEntity.ok(count);
	}
}
