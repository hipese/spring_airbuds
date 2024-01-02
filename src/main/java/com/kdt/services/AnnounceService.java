package com.kdt.services;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.AnnounceBoard;
import com.kdt.dto.AnnounceBoardDTO;
import com.kdt.mappers.AnnounceMapper;
import com.kdt.repositories.AnnounceRepository;

@Service
public class AnnounceService {
	
	private static final Logger Logger = LoggerFactory.getLogger(AnnounceService.class);

	@Autowired
	private AnnounceRepository aRepo;
	
	@Autowired
	private AnnounceMapper aMapper;
	
	public void insertAnnounce(AnnounceBoardDTO dto) {
		AnnounceBoard ab = aMapper.toEntity(dto);
		aRepo.save(ab);
	}
	
	public List<AnnounceBoardDTO> selectAll(){
		List<AnnounceBoard> abList = aRepo.selectAllByDesc();
		List<AnnounceBoardDTO> dtoList = aMapper.toDtoList(abList);
		return dtoList;
	}
	
	public void addViewCount(Long seq) {
		AnnounceBoard ab = aRepo.findById(seq).get();
		AnnounceBoardDTO dto = new AnnounceBoardDTO();
		dto.setAnnounceViewCount(ab.getAnnounceViewCount()+1);
		
		aMapper.updateEntityFromDto(dto, ab);
		aRepo.save(ab);		
	}
	
	public AnnounceBoardDTO getAnnounceDetail(Long seq) {
		AnnounceBoard ab = aRepo.findById(seq).get();
		AnnounceBoardDTO dto = aMapper.toDto(ab);
		return dto;
	}
	
	public void deleteAnnounce(Long seq, Principal id) {
		Logger.info(id.toString());
		
		AnnounceBoard ab = aRepo.findById(seq).get();
		Logger.info(ab.getAnnounceWriter());
		if(ab.getAnnounceWriter().equals(id) || ab.getAnnounceWriter().equals("admin")) {
			aRepo.delete(ab);
		}
		else {
			Logger.warn("삭제 행위자와 삭제 대상의 작성자가 다릅니다.");
		}
	}
}
