package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.MusicLike;
import com.kdt.domain.entity.SingerFollow;

public interface SingerFollowRepository extends JpaRepository<SingerFollow, Long>{
	
	 Long countByMemberIdAndSingerId(String memberId, String singerId);	 
	 
	 @Query("select sf from SingerFollow sf where sf.memberId = ?1% and sf.singerId = ?2%")
	 List<SingerFollow> selectAllByMemberAndSinger(String memberId, String singerId);
	 
	 Long countByMemberId(String memberId);
	 
	 Long countBySingerId(String singerId);
}
