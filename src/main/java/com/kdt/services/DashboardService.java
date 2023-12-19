package com.kdt.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.MemberAgeView;
import com.kdt.dto.MemberAgeViewDTO;
import com.kdt.mappers.MemberAgeViewMapper;
import com.kdt.repositories.MemberAgeViewRepository;
import com.kdt.repositories.ReportRepository;
import com.kdt.repositories.TrackRepository;

@Service
public class DashboardService {
	
	@Autowired
	private ReportRepository rRepo;
	
	@Autowired
	private TrackRepository tRepo;
	
	@Autowired
	private MemberAgeViewRepository mavRepo;
	
	@Autowired
	private MemberAgeViewMapper mavMapper;
	
	public List<Map<String,Object>> getList() {
		List<Map<String,Object>> list = rRepo.selectReportByMonth();
		return list;
	}
	
	public List<Map<String,Object>> getMusicData() {
		List<Map<String,Object>> list = tRepo.selectReleasedMusic();
		return list;
	}
	
	public List<MemberAgeViewDTO> getMemberAge(){
		List<MemberAgeView> mav = mavRepo.findAll();
		List<MemberAgeViewDTO> dtoList = mavMapper.toDtoList(mav);
		return dtoList;
	}

}
