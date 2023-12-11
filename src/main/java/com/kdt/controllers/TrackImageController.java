package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.services.TrackImageService;

@RestController
@RequestMapping("/api/trackImage")
public class TrackImageController {

	@Autowired
	private TrackImageService tService;
	
	
}
