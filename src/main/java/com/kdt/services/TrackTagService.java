package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.Track;
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
		List<TrackTag> list = tagRepo.findByMusicTags_tagId(tag);
		List<TrackTagDTO> dtoList = tagMapper.toDtoList(list);
		return dtoList;

	}
}
