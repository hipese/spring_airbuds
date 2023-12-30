package com.kdt.services;

import java.io.File;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
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
	
	private final Storage storage = StorageOptions.getDefaultInstance().getService();
	private final String bucketName = "an_airbuds";
	
	
	public void getPost(QnaDTO dto, MultipartFile[] files) throws Exception{
		Qna qna = qMapper.toEntity(dto);
		Long seq = qRepo.save(qna).getQnaSeq();
		
		if (files != null && files.length > 0) {
			for (MultipartFile file : files) {
				String oriName = file.getOriginalFilename();
				String sysName = UUID.randomUUID() + "_" + oriName;
				
				BlobId uploadblobId = BlobId.of(bucketName, "/uploads/" + sysName);// 경로이름 지정한 장소
				BlobInfo uploadblobInfo = BlobInfo.newBuilder(uploadblobId).build();
				
				// 파일을 GCS에 업로드하고 Blob 객체를 받습니다.
				Blob trackblob = storage.create(uploadblobInfo, file.getBytes());
				
				QnaFileDTO fileDto = new QnaFileDTO();
				fileDto.setParentSeq(seq);
				fileDto.setOriName(oriName);
				fileDto.setSysName(trackblob.getMediaLink());
				QnaFile qnaFile = qfMapper.toEntity(fileDto);
				qfRepo.save(qnaFile);
				
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
