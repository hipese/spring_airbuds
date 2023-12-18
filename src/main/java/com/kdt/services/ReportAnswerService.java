package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.ReportAnswer;
import com.kdt.dto.ReportAnswerDTO;
import com.kdt.mappers.ReportAnswerMapper;
import com.kdt.repositories.ReportAnswerRepository;

@Service
public class ReportAnswerService {

	@Autowired
	private ReportAnswerRepository raRepo;
	
	@Autowired
	private ReportAnswerMapper raMapper;
	
	public void insertAnswer(ReportAnswerDTO dto) {
		ReportAnswer Report = raMapper.toEntity(dto);
		raRepo.save(Report);
	}
	
	public List<ReportAnswerDTO> selectReplies(Long seq){
		List<ReportAnswer> ReportList = raRepo.selectAllByParentSeq(seq);
		List<ReportAnswerDTO> dtoList = raMapper.toDtoList(ReportList);
		return dtoList;
	}
	
}
