package com.kdt.services;

import java.io.File;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.domain.entity.Qna;
import com.kdt.domain.entity.QnaAnswer;
import com.kdt.domain.entity.QnaFile;
import com.kdt.dto.QnaDTO;
import com.kdt.dto.QnaFileDTO;
import com.kdt.mappers.QnaAnswerMapper;
import com.kdt.mappers.QnaFileMapper;
import com.kdt.mappers.QnaMapper;
import com.kdt.repositories.QnaAnswerRepository;
import com.kdt.repositories.QnaFileRepository;
import com.kdt.repositories.QnaRepository;

import jakarta.transaction.Transactional;

@Service
public class QnaService {

	@Autowired
	private QnaRepository qRepo;
	
	@Autowired 
	private QnaMapper qMapper;
	
	@Autowired 
	private QnaFileRepository qfRepo;
	
	@Autowired
	private QnaFileMapper qfMapper;
	
	@Autowired
	private QnaAnswerRepository qaRepo;
	
	@Autowired
	private QnaAnswerMapper qaMapper;
	
	public static String upload = "/uploads";
	
	public void getPost(QnaDTO dto, MultipartFile[] files) throws Exception{
		Qna qna = qMapper.toEntity(dto);
		Long seq = qRepo.save(qna).getQnaSeq();
		
		File uploadPath = new File(upload);

		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		if (files != null && files.length > 0) {
			for (MultipartFile file : files) {
				String oriName = file.getOriginalFilename();
				String sysName = UUID.randomUUID() + "_" + oriName;

				QnaFileDTO fileDto = new QnaFileDTO();
				fileDto.setParentSeq(seq);
				fileDto.setOriName(oriName);
				fileDto.setSysName(sysName);
				QnaFile qnaFile = qfMapper.toEntity(fileDto);
				qfRepo.save(qnaFile);
				
				file.transferTo(new File(uploadPath, sysName));
			}
		}
	}
	
	public List<QnaDTO> selectAll(){
		List<Qna> qnaList = qRepo.findAllByFetchJoin();
		List<QnaDTO> dtoList = qMapper.toDtoList(qnaList);
		return dtoList;
	}
	
	public QnaDTO getContents(Long seq) throws Exception{
		Qna qna = qRepo.findById(seq).get();
		QnaDTO dto = qMapper.toDto(qna);
		return dto;
	}
	
	public void deletePost(Long seq, Principal id) throws Exception{
		Qna qna = qRepo.findById(seq).get();
		
		//File delete
		List<QnaFile> qnaFile = qfRepo.selectAllByParentSeq(seq);
		if(qnaFile.size() > 0) {
			for(QnaFile qf : qnaFile) {
				String oriName = qf.getOriName();
				String sysName = qf.getSysName();
				File f = new File(upload+"/"+sysName);
				f.delete();		
				qfRepo.delete(qf);
			}
		}
		
		//Answer delete
		List<QnaAnswer> qaList = qaRepo.selectAllByParentSeq(seq);
		if(qaList.size() > 0) {
			for(QnaAnswer qa : qaList) {
				qaRepo.delete(qa);
			}
		}
		
		//Post Delete
		qRepo.delete(qna);
	}
	
	public void updateState(QnaDTO dto) {
		Qna q = qRepo.findById(dto.getQnaSeq()).get();
		qMapper.updateEntityFromDto(dto, q);
		qRepo.save(q);
	}
}
