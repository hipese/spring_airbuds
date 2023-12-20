package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.TrackReplyLike;
import com.kdt.dto.TrackReplyLikeDTO;
import com.kdt.mappers.TrackReplyLikeMapper;
import com.kdt.repositories.TrackReplyLikeRepository;

@Service
public class TrackReplyLikeService {

	@Autowired
	private TrackReplyLikeRepository rlRepo;
	
	@Autowired
	private TrackReplyLikeMapper rlMapper;
	
	public Long insert(TrackReplyLikeDTO dto) {
		List<TrackReplyLike> rlList = rlRepo.selectAllByIdAndReply(dto.getId(), dto.getReplySeq());
		if(rlList.size() > 0) {
			return null;
		}else {
			TrackReplyLike rl = rlMapper.toEntity(dto);
			Long likeseq = rlRepo.save(rl).getSeq();
			return likeseq;
		}		
		
	}
	
	public List<TrackReplyLikeDTO> selectAllById(String id){
		List<TrackReplyLike> list = rlRepo.selectAllById(id);
		List<TrackReplyLikeDTO> dto = rlMapper.toDtoList(list);
		return dto;
	}

	public void deleteFavorite(TrackReplyLikeDTO dto) {
		List<TrackReplyLike> rl = rlRepo.selectAllByIdAndReply(dto.getId(), dto.getReplySeq());
		if(rl != null) {
			for(TrackReplyLike r : rl) {
				rlRepo.delete(r);
			}
			
		}
	}
}
