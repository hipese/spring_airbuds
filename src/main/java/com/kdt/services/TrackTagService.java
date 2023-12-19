package com.kdt.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.TrackTag;
import com.kdt.dto.TrackTagDTO;
import com.kdt.mappers.TrackTagMapper;
import com.kdt.repositories.TrackTagRepository;

import jakarta.transaction.Transactional;

@Service
public class TrackTagService {

	@Autowired
	private TrackTagRepository tagRepo;

	@Autowired
	private TrackTagMapper tagMapper;

	@Transactional
	public List<TrackTagDTO> selectTag(Long tag) {
		List<TrackTag> list = tagRepo.findFirst10ByMusicTags_tagId(tag, Sort.by(Sort.Direction.DESC, "id"));
		List<TrackTagDTO> dtoList = tagMapper.toDtoList(list);
		return dtoList;

	}
	
	public List<TrackTagDTO> selectTagById(Long id){
	    
		List<TrackTag> entitys=tagRepo.findAllByTrackTagTrackId(id);
		System.out.println("추출은 되냐?"+entitys.get(0).getId());
		List<TrackTagDTO> dtos = tagMapper.toDtoList(entitys);
		return  dtos;
	}
}
