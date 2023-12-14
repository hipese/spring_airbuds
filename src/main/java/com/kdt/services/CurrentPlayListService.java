package com.kdt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.CurrentPlayList;
import com.kdt.dto.CurrentPlayListDTO;
import com.kdt.mappers.CurrentPlayListMapper;
import com.kdt.repositories.CurrentPlayListRepository;

@Service
public class CurrentPlayListService {

	@Autowired
	private CurrentPlayListRepository cpRepo;
	
	@Autowired
	private CurrentPlayListMapper cpMapper;
	
	public void insert(CurrentPlayListDTO dto) throws Exception{
	    CurrentPlayList cplist = cpMapper.toEntity(dto);
	    cpRepo.save(cplist);
	}

}
