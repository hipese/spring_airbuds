package com.kdt.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
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
	
	private static final Logger Logger = LoggerFactory.getLogger(QnaAnswerService.class);

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
	
	public void deleteAnswer(Long seq, Principal id) {
		QnaAnswer qa = qnaRepo.findById(seq).get();
		if(qa.getAnswerWriter().equals(id)) {
			qnaRepo.delete(qa);
		}else {
			Logger.warn("삭제 행위자와 삭제 대상의 작성자가 다릅니다.");
		}
		
	}
	
	public void updateAnswer(QnaAnswerDTO dto) {
		QnaAnswer qa = qnaRepo.findById(dto.getAnswerSeq()).get();
		qnaMapper.updateEntityFromDto(dto, qa);
		qnaRepo.save(qa);
	}
	
}
