package com.kdt.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.repositories.ReportRepository;

@Service
public class DashboardService {
	
	@Autowired
	private ReportRepository rRepo;
	
	public List<Map<String,Object>> getList() {
		List<Map<String,Object>> list = rRepo.selectReportByMonth();
		return list;
	}

}
