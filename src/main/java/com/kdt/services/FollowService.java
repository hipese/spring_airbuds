package com.kdt.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.FollowingSingerView;
import com.kdt.domain.entity.MusicLike;
import com.kdt.domain.entity.SingerFollow;
import com.kdt.dto.FollowingSingerViewDTO;
import com.kdt.dto.SingerFollowDTO;
import com.kdt.mappers.FollowingSingerViewMapper;
import com.kdt.mappers.SingerFollowMapper;
import com.kdt.repositories.FollowingSingerViewRepository;
import com.kdt.repositories.SingerFollowRepository;

@Service
public class FollowService {
	
	@Autowired
	private SingerFollowMapper sfMapper;
	
	@Autowired
	private SingerFollowRepository sfRepo;
	
	@Autowired
	private FollowingSingerViewRepository fsvRepo;
	
	@Autowired
	private FollowingSingerViewMapper fsvMapper;
	
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
	
//	public List<FollowingSingerViewDTO> getMyFollow(String memberId){
//		List<FollowingSingerView> fsv = fsvRepo.findByFollower(memberId);
//		List<FollowingSingerViewDTO> dtoList = fsvMapper.toDtoList(fsv);
//		HashMap<String,Object> data = new HashMap<>();
//		for(FollowingSingerViewDTO dto : dtoList) {
//			Long value = fsvRepo.countBySinger(dto.getSinger());
//			data.put("following", dto);
//			data.put("follower",value);
//		}
//		
//		return dtoList;
//	}
	public List<HashMap<String,Object>> getMyFollow(String memberId){
		List<FollowingSingerView> fsv = fsvRepo.findByFollower(memberId);
		for(FollowingSingerView f : fsv) {
			System.out.println("★★"+f.getSinger());
		}
		List<FollowingSingerViewDTO> dtoList = fsvMapper.toDtoList(fsv);
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		for(FollowingSingerViewDTO dto : dtoList) {
			System.out.println("★ follower : "+dto.getFollower()+" singer : "+dto.getSinger());
			HashMap<String,Object> data = new HashMap<>();
			Long value = fsvRepo.countBySinger(dto.getSinger());
			data.put("following", dto);
			data.put("follower",value);
			list.add(data);
		}		
		return list;
	}

}


