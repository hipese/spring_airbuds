package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.AlbumTagList;
import com.kdt.dto.AlbumTagListDTO;
import com.kdt.mappers.AlbumTagListMapper;
import com.kdt.repositories.AlbumTagListRepository;

@Service
public class AlbumTagListService {
	
	
	@Autowired
	AlbumTagListRepository ATLrepo;
	
	@Autowired
	AlbumTagListMapper ATLmapper;
	
	public List<AlbumTagListDTO> selectAll(){
		List<AlbumTagListDTO> dtos=ATLmapper.toDtoList(ATLrepo.findAll());
		return dtos;
	}
}
