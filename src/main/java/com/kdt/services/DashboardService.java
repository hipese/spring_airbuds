package com.kdt.services;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.DailyStreaming;
import com.kdt.domain.entity.DailyVisit;
import com.kdt.domain.entity.MemberAgeView;
import com.kdt.domain.entity.QnaAnswer;
import com.kdt.dto.DailyStreamingDTO;
import com.kdt.dto.DailyVisitDTO;
import com.kdt.dto.MemberAgeViewDTO;
import com.kdt.mappers.DailyStreamingMapper;
import com.kdt.mappers.DailyVisitMapper;
import com.kdt.mappers.MemberAgeViewMapper;
import com.kdt.mappers.ReportMapper;
import com.kdt.repositories.DailyStreamingRepository;
import com.kdt.repositories.DailyVisitRepository;
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
	
	@Autowired
	private ReportMapper rMapper;
	
	@Autowired
	private DailyVisitRepository dvRepo;
	
	@Autowired
	private DailyVisitMapper dvMapper;
	
	@Autowired
	private DailyStreamingRepository dsRepo;
	
	@Autowired
	private DailyStreamingMapper dsMapper;
	
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
	
	public Long getReportCount() {
		Long count = rRepo.selectReportCountByDay();
		return count;
	}
	
	public void insertVisit(DailyVisitDTO dto) {
		DailyVisit dv = dvRepo.selectVisitByDate();
		if(dv == null) {			
			dto.setVisitCount(1L);
			dv = dvMapper.toEntity(dto);
			dvRepo.save(dv);
		}else {
			dto.setVisitCount(dv.getVisitCount()+1L);
			dvMapper.updateEntityFromDto(dto, dv);;
			dvRepo.save(dv);
		}
	}
	
	public Long getDailtCount() {
		Long count = dvRepo.selectVisitCount();
		return count;
	}
	
	public void insertStreamCount(DailyStreamingDTO dto) {
		DailyStreaming ds = dsRepo.selectStreamByDate(dto.getTrackId());
		if(ds == null) {			
			dto.setStreamCount(1L);
			ds = dsMapper.toEntity(dto);
			dsRepo.save(ds);
		}else {
			dto.setStreamCount(ds.getStreamCount()+1L);
			dsMapper.updateEntityFromDto(dto, ds);
			dsRepo.save(ds);
		}
	}
	
	public Long getStreamCount() {
		Long count = dsRepo.selectStreamCount();
		return count;
	}

}
