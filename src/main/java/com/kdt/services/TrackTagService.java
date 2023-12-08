package com.kdt.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.TrackTag;
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
	
	public List<Long> selectRomance() {
	    List<TrackTag> romanceTags = tagRepo.findByTag("로맨틱한");
	    List<Long> romanceTrackIds = romanceTags.stream()
	                                            .map(TrackTag::getTrackId)
	                                            .collect(Collectors.toList());
	    return romanceTrackIds;
	}
}
