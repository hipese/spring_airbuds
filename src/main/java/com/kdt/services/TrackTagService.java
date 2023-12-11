package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.Track;
import com.kdt.domain.entity.TrackImages;
import com.kdt.domain.entity.TrackTag;
import com.kdt.dto.TrackTagDTO;
import com.kdt.mappers.TrackTagMapper;
import com.kdt.repositories.TrackTagRepository;

@Service
public class TrackTagService {

	@Autowired
	private TrackTagRepository tagRepo;

	@Autowired
	private TrackTagMapper tagMapper;

	public List<TrackTagDTO> selectTag(String tag) {
		List<TrackTag> list = tagRepo.findByTag(tag);
		
		System.out.println(list.size()+ "zzzzz" );
		System.out.println(list.get(0).getTrack().size() + "xxxxxxxxxx");
		for(Track t : list.get(0).getTrack()) {
			
			System.out.println(t.getTrackImages().size() + "cccccccccc");
		}
		List<TrackTagDTO> dtoList = tagMapper.toDtoList(list);
		return dtoList;

	}
}
