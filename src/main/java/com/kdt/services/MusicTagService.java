package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.MusicTags;
import com.kdt.dto.MusicTagDTO;
import com.kdt.mappers.MusicTagMapper;
import com.kdt.repositories.MusicTagRepository;

@Service
public class MusicTagService {

	@Autowired
	MusicTagRepository mtagRepo;
	
	@Autowired
	MusicTagMapper mtagMapper;
	
	public List<MusicTagDTO> selectAll(){
		List<MusicTags> entiry=mtagRepo.findAll();
		
		List<MusicTagDTO> dtos=mtagMapper.toDtoList(entiry);
		return dtos;
	}
	
}
