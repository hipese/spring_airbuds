package com.kdt.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.MusicLike;
import com.kdt.domain.entity.SingerFollow;
import com.kdt.dto.SingerFollowDTO;
import com.kdt.mappers.SingerFollowMapper;
import com.kdt.repositories.MemberRepository;
import com.kdt.repositories.SingerFollowRepository;

@Service
public class FollowService {
	
	@Autowired
	private SingerFollowMapper sfMapper;
	
	@Autowired
	private SingerFollowRepository sfRepo;
	
	@Autowired
	private MemberRepository mRepo;
	
	
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
	
	public List<HashMap<String, Object>> getMyFollow(String memberId){
		List<SingerFollow> sf = sfRepo.findAll();
		List<SingerFollowDTO> dtoList = sfMapper.toDtoList(sf);
		List<HashMap<String, Object>> result = new ArrayList<>();
		for(SingerFollowDTO dto : dtoList) {
			if(dto.getMemberId().equals(memberId)) {
				HashMap<String, Object> map = new HashMap<>();
				Long followerCount = sfRepo.countBySingerId(dto.getSingerId());
				map.put("follower", dto.getMemberId());
				map.put("singer", dto.getSingerId());
				map.put("profile_image", dto.getMember().getProfile_image());
				map.put("followerNumber",followerCount);
				result.add(map);
			}
		}
		return result;
	}

}


