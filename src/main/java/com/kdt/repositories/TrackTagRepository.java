package com.kdt.repositories;

import java.util.List;


import org.springframework.data.domain.Sort;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.TrackImages;
import com.kdt.domain.entity.TrackTag;

import jakarta.transaction.Transactional;

public interface TrackTagRepository extends JpaRepository<TrackTag, Long> {
	@EntityGraph(attributePaths = {"track","musicTags","track.trackImages"})
	List<TrackTag> findFirst10ByMusicTags_tagId(Long tagId, Sort sort);

	@Modifying
	@Transactional
	@Query("DELETE FROM TrackTag t WHERE t.track.id = :trackId")
	void deleteAllByTrackTagTrackId(@Param("trackId") Long trackId);
}