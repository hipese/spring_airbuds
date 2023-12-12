package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.Qna;
import com.kdt.domain.entity.QnaAnswer;
import com.kdt.dto.QnaAnswerDTO;
import com.kdt.mappers.QnaAnswerMapper;
import com.kdt.repositories.QnaAnswerRepository;

@Service
public class QnaAnswerService {

	@Autowired
	private QnaAnswerRepository qnaRepo;
	
	@Autowired
	private QnaAnswerMapper qnaMapper;
	
	public void insertAnswer(QnaAnswerDTO dto) {
		QnaAnswer qna = qnaMapper.toEntity(dto);
		qnaRepo.save(qna);
	}
	
	public List<QnaAnswerDTO> selectReplies(Long seq){
		List<QnaAnswer> qnaList = qnaRepo.selectAllByParentSeq(seq);
		List<QnaAnswerDTO> dtoList = qnaMapper.toDtoList(qnaList);
		return dtoList;
	}
	
	public void deleteAnswer(Long seq) {
		QnaAnswer qa = qnaRepo.findById(seq).get();
		qnaRepo.delete(qa);
	}
	
}
