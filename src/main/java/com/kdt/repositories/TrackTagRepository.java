package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kdt.domain.entity.TrackTag;

@Repository
public interface TrackTagRepository extends JpaRepository<TrackTag, Long> {
	List<TrackTag> findByTag(String tag);
}
