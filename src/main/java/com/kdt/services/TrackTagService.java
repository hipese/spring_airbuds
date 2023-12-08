package com.kdt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dto.MusicTagDTO;
import com.kdt.dto.TrackTagDTO;
import com.kdt.mappers.TrackImageMapper;
import com.kdt.mappers.TrackMapper;
import com.kdt.mappers.TrackTagMapper;
import com.kdt.repositories.TrackImageRepository;
import com.kdt.repositories.TrackRepository;
import com.kdt.repositories.TrackTagRepository;

@Service
public class TrackTagService {

	@Autowired
	private TrackRepository tRepo;
	@Autowired
	private TrackTagRepository tagRepo;
	@Autowired
	private TrackImageRepository imageRepo;

	@Autowired
	private TrackMapper tMapper;
	@Autowired
	private TrackTagMapper tagMapper;
	@Autowired
	private TrackImageMapper imageMapper;
	
//	public List<TrackTagDTO> selectTag(MusicTagDTO musicTag) {
//		List<TrackTagDTO> result = new ArrayList<>();
//		List<String> tagNames = musicTag.getTagName();
//		 for (String tagName : tagNames) {
//
//	            List<TrackTagDTO> dtos = tagRepo.findByTagName(tagName); // 이 메소드는 TrackTagRepository에 구현되어야 한다.
//	            result.addAll(dtos);
//	        }
//
//	        return result;
//	}
}
