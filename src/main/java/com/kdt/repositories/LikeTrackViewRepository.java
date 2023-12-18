package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.LikeTrackView;
import com.kdt.domain.entity.MusicLike;

public interface LikeTrackViewRepository extends JpaRepository<LikeTrackView, Long>{

	@Query("select ltv from LikeTrackView ltv where ltv.writeId = ?1%")
	List<LikeTrackView> selectCountByName(String writeId);
}
