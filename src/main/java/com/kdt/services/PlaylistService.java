package com.kdt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdt.domain.entity.Playlist;
import com.kdt.dto.PlaylistDTO;
import com.kdt.mappers.PlaylistMapper;
import com.kdt.repositories.PlaylistRepository;

@Service
public class PlaylistService {

	@Autowired
	private PlaylistRepository plRepo;
	
	@Autowired
	private PlaylistMapper plMapper;
	
	@Transactional
	public void insert(PlaylistDTO dto) {
		Playlist pl = plMapper.toEntity(dto);
		plRepo.save(pl);
	}
}
