package com.kdt.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.services.DashboardService;
import com.kdt.services.MemberService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	@Autowired
	private MemberService mService;
	
	@Autowired
	private DashboardService dService;
	
	@GetMapping
	public ResponseEntity<List<HashMap<String,Object>>> getReport(){
		List<Map<String,Object>> list = dService.getList();
		List<HashMap<String, Object>> resultList = new ArrayList<>();

        for (Map<String, Object> originalMap : list) {
            HashMap<String, Object> resultMap = new HashMap<>(originalMap);
            resultList.add(resultMap);
        }
		
		return ResponseEntity.ok(resultList);
	}
}
