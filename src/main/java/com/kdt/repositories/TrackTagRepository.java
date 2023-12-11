package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.TrackTag;

public interface TrackTagRepository extends JpaRepository<TrackTag, Long> {
	@EntityGraph(attributePaths = {"track","musicTags","track.trackImages"})
    List<TrackTag> findByMusicTags_tagId(Long tag);
}