package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.SanctionView;
import com.kdt.domain.entity.Track;
import com.kdt.domain.entity.TrackImages;
import com.kdt.dto.SanctionViewDTO;
import com.kdt.dto.TrackDTO;
import com.kdt.dto.TrackImageDTO;
import com.kdt.mappers.SanctionViewMapper;
import com.kdt.mappers.TrackImageMapper;
import com.kdt.mappers.TrackMapper;
import com.kdt.repositories.SanctionViewRepository;
import com.kdt.repositories.TrackImageRepository;
import com.kdt.repositories.TrackRepository;

@Service
public class SanctionService {

	@Autowired 
	private SanctionViewRepository svRepo;

	@Autowired
	private SanctionViewMapper svMapper;

	@Autowired
	private TrackRepository tRepo;

	@Autowired
	private TrackMapper tMapper;

	@Autowired
	private TrackImageRepository tiRepo;

	@Autowired
	private TrackImageMapper tiMapper;

	public List<SanctionViewDTO> selectAll() {
		List<SanctionView> entity = svRepo.findAll();
		List<SanctionViewDTO> dtos = svMapper.toDtoList(entity);
		return dtos;
	}

	// 제재 리스트 받아오는 코드
	public List<SanctionViewDTO> selectSanctionAll() {
		List<SanctionView> entity = svRepo.findByBanTrue();
		List<SanctionViewDTO> dtos = svMapper.toDtoList(entity);
		return dtos;
	}

	// 제재 완료
	public void changeSanctionState(Long trackId, String reason) {
		Track r = tRepo.findById(trackId).get();
		TrackDTO dto = new TrackDTO();
		dto.setBan(1L);
		dto.setBanReason(reason);
		tMapper.updateEntityFromDto(dto, r);
		tRepo.save(r);	
	}

	// 이미지 제재
	public void changeImage(Long trackId) {
		TrackImages t = tiRepo.findBytrackId(trackId);
		TrackImageDTO dto = new TrackImageDTO();
		dto.setImagePath("");
		tiMapper.updateEntityFromDto(dto, t);
		tiRepo.save(t);	
	}

	// 해제 완료
	public void releaseSanction(Long trackId) {
		Track r = tRepo.findById(trackId).get();
		TrackDTO dto = new TrackDTO();
		dto.setBan(0L);
		dto.setBanReason("");
		tMapper.updateEntityFromDto(dto, r);
		tRepo.save(r);	
	}

}
