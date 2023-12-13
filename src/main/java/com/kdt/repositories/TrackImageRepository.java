package com.kdt.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.TrackImages;

public interface TrackImageRepository extends JpaRepository<TrackImages, Long> {
	
  @Query("SELECT t FROM TrackImages t WHERE t.trackId = :trackId")
	TrackImages findByTrackImagesTrackId(@Param("trackId") Long trackId);
}
