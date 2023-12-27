package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.AnnounceBoard;
import com.kdt.dto.AnnounceBoardDTO;
import com.kdt.mappers.AnnounceMapper;
import com.kdt.repositories.AnnounceRepository;

@Service
public class AnnounceService {

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
	
	public void deleteAnnounce(Long seq) {
		AnnounceBoard ab = aRepo.findById(seq).get();
		aRepo.delete(ab);
	}
}
