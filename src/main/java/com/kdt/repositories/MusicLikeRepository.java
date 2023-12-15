package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.MusicLike;
import com.kdt.domain.entity.QnaFile;

public interface MusicLikeRepository extends JpaRepository<MusicLike, Long>{
	@Query("select ml from MusicLike ml where ml.userId = ?1%")
	List<MusicLike> selectAllByName(String userId);
	
	@Query("select ml from MusicLike ml where ml.userId = ?1% and ml.trackId = ?2%")
	List<MusicLike> selectAllByNameAndTrack(String userId, Long trackId);
}
