package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.Qna;
import com.kdt.dto.QnaDTO;
import com.kdt.mappers.QnaMapper;
import com.kdt.repositories.QnaRepository;

@Service
public class QnaService {

	@Autowired
	private QnaRepository qRepo;
	
	@Autowired 
	private QnaMapper qMapper;
	
	public void getPost(QnaDTO dto) {
		Qna qna = qMapper.toEntity(dto);
		qRepo.save(qna);
	}
	
	public List<QnaDTO> selectAll(){
		List<Qna> qnaList = qRepo.findAll();
		List<QnaDTO> dtoList = qMapper.toDtoList(qnaList);
		return dtoList;
	}
}
