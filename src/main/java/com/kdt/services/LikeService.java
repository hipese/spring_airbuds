package com.kdt.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.CurrentPlayList;
import com.kdt.domain.entity.MusicLike;
import com.kdt.domain.entity.MyMusicLikes;
import com.kdt.domain.entity.Track;
import com.kdt.dto.CurrentPlayListDTO;
import com.kdt.dto.MusicLikeDTO;
import com.kdt.dto.MyMusicLikesDTO;
import com.kdt.mappers.MusicLikeMapper;
import com.kdt.mappers.MyMusicLikesMapper;
import com.kdt.repositories.MusicLikeRepository;
import com.kdt.repositories.MyMusicLikesRepository;

import jakarta.transaction.Transactional;

@Service
public class LikeService {
	
	@Autowired
	private MusicLikeRepository mlRepo;
	
	@Autowired
	private MusicLikeMapper mlMapper;
	
	@Autowired
	private MyMusicLikesRepository mmlRepo;
	
	@Autowired
	private MyMusicLikesMapper mmlMapper;
	
	public Long insertFavorite(MusicLikeDTO dto) {
		List<MusicLike> mlList = mlRepo.selectAllByNameAndTrack(dto.getUserId(), dto.getTrackId());
		if(mlList.size() > 0) {
			if(mlList.size() > 1) {
				for(int i=0; i<mlList.size()-1; i++) {
					mlRepo.deleteByUserIdAndTrackId(dto.getUserId(), dto.getTrackId());
				}
			}
			return null;
		}else {
			MusicLike ml = mlMapper.toEntity(dto);
			Long likeseq = mlRepo.save(ml).getLikeSeq();
			return likeseq;
		}		
		
	}
	
	public List<MusicLikeDTO> selectAllById(String userId){
		List<MusicLike> list = mlRepo.selectAllByName(userId);
		List<MusicLikeDTO> dto = mlMapper.toDtoList(list);
		return dto;
	}

	public void deleteFavorite(MusicLikeDTO dto) {
		mlRepo.deleteByUserIdAndTrackId(dto.getUserId(), dto.getTrackId());
	}
	
	public List<MusicLikeDTO> selectAllTracksById(Principal id) {
		String id2 = id.getName();
		List<MusicLike> playlists = mlRepo.findAllByIdStartingWith(id2, Sort.by(Sort.Order.desc("likeSeq")));
		List<MusicLikeDTO> dtos = mlMapper.toDtoList(playlists);
		return dtos;
	}

	public List<MusicLikeDTO> selectById(Principal id, int page, int pageSize) {
		String id2 = id.getName();
		PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("likeSeq")));
		List<MusicLike> playlists = mlRepo.findByIdStartingWith(id2, pageRequest);
		List<MusicLikeDTO> dtos = mlMapper.toDtoList(playlists);
		return dtos;
	}
	
	public List<MyMusicLikesDTO> getLikeCount(String id) {
		List<MyMusicLikes> mml = mmlRepo.selectById(id);
		List<MyMusicLikesDTO> dtoList = mmlMapper.toDtoList(mml);
		return dtoList;
	}
}
