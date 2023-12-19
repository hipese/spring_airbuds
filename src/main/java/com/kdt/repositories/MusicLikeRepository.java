package com.kdt.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.MusicLike;
import com.kdt.domain.entity.Playlist;

public interface MusicLikeRepository extends JpaRepository<MusicLike, Long>{
	@Query("select ml from MusicLike ml where ml.userId = ?1%")
	List<MusicLike> selectAllByName(String userId);
	
	@Query("select ml from MusicLike ml where ml.userId = ?1% and ml.trackId = ?2%")
	List<MusicLike> selectAllByNameAndTrack(String userId, Long trackId);
	
	@Query("SELECT c FROM MusicLike c LEFT JOIN FETCH c.tracks WHERE c.userId LIKE CONCAT(:userId, '%')")
	List<MusicLike> findByIdStartingWith(@Param("userId") String userId, Pageable pageable);

	@Query("SELECT c FROM MusicLike c LEFT JOIN FETCH c.tracks WHERE c.userId LIKE CONCAT(:userId, '%')")
	List<MusicLike> findAllByIdStartingWith(@Param("userId") String userId, Sort sort);
}
