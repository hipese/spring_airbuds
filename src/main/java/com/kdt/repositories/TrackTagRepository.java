package com.kdt.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.TrackTag;

public interface TrackTagRepository extends JpaRepository<TrackTag, Long> {
	@EntityGraph(attributePaths = {"track","musicTags","track.trackImages"})
	List<TrackTag> findFirst10ByMusicTags_tagId(Long tagId, Sort sort);

	
}