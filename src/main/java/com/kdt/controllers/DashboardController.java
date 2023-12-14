package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.services.MemberService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	@Autowired
	private MemberService mService;
	
	
}
