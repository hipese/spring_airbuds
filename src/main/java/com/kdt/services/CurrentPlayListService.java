package com.kdt.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public void insert(CurrentPlayListDTO dto) throws Exception {
		// 삭제
		cpRepo.deleteByIdAndTrackId(dto.getId(), dto.getTrackId());

		// 삽입
		cpRepo.insertCurrentPlayList(dto.getSeq(), dto.getTrackId(), dto.getId());
	}

	public List<CurrentPlayListDTO> selectAllById(Principal id) {
		String id2 = id.getName();
		List<CurrentPlayList> playlists = cpRepo.findAllByIdStartingWith(id2, Sort.by(Sort.Order.desc("seq")));
		List<CurrentPlayListDTO> dtos = cpMapper.toDtoList(playlists);
		return dtos;
	}

	public List<CurrentPlayListDTO> selectById(Principal id, int page, int pageSize) {
		String id2 = id.getName();
		PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("seq")));
		List<CurrentPlayList> playlists = cpRepo.findByIdStartingWith(id2, pageRequest);
		List<CurrentPlayListDTO> dtos = cpMapper.toDtoList(playlists);
		return dtos;
	}

}
