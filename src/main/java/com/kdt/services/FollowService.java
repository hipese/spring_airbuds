package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.MusicLike;
import com.kdt.domain.entity.SingerFollow;
import com.kdt.dto.SingerFollowDTO;
import com.kdt.mappers.SingerFollowMapper;
import com.kdt.repositories.SingerFollowRepository;

@Service
public class FollowService {
	
	@Autowired
	private SingerFollowMapper sfMapper;
	
	@Autowired
	private SingerFollowRepository sfRepo;
	
	public void insertFollow(SingerFollowDTO dto) {
		SingerFollow sf = sfMapper.toEntity(dto);
		sfRepo.save(sf);
	}
	
	public Boolean getFollowState(SingerFollowDTO dto) {
		Long count = sfRepo.countByMemberIdAndSingerId(dto.getMemberId(),dto.getSingerId());
		if(count > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void deleteFollow(SingerFollowDTO dto) {
		List<SingerFollow> sf = sfRepo.selectAllByMemberAndSinger(dto.getMemberId(), dto.getSingerId());
		if(sf != null) {
			for(SingerFollow s : sf) {
				sfRepo.delete(s);
			}			
		}
	}
	
	public Long getFollowers(String singerId) {
		Long followers = sfRepo.countBySingerId(singerId);
		return followers;
		
	}
	
	public Long getFollowing(String memberId) {
		Long followings = sfRepo.countByMemberId(memberId);
		return followings;
		
	}

}


