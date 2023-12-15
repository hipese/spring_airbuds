package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.MusicLike;
import com.kdt.dto.MusicLikeDTO;
import com.kdt.mappers.MusicLikeMapper;
import com.kdt.repositories.MusicLikeRepository;

@Service
public class LikeService {
	
	@Autowired
	private MusicLikeRepository mlRepo;
	
	@Autowired
	private MusicLikeMapper mlMapper;
	
	public void insertFavorite(MusicLikeDTO dto) {
		List<MusicLike> mlList = mlRepo.selectAllByNameAndTrack(dto.getId(), dto.getTrackId());
		if(mlList.size() > 0) {
			return;
		}else {
			MusicLike ml = mlMapper.toEntity(dto);
			mlRepo.save(ml);
		}
		
	}
	
	public List<MusicLikeDTO> selectAllById(String id){
		List<MusicLike> list = mlRepo.selectAllByName(id);
		List<MusicLikeDTO> dto = mlMapper.toDtoList(list);
		return dto;
	}

	public void deleteFavorite(MusicLikeDTO dto) {
		List<MusicLike> ml = mlRepo.selectAllByNameAndTrack(dto.getId(), dto.getTrackId());
		if(ml != null) {
			for(MusicLike m : ml) {
				mlRepo.delete(m);
			}
			
		}
	}

}
