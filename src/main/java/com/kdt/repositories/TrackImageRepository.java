package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.TrackImage;

public interface TrackImageRepository extends JpaRepository<TrackImage, Long> {

}
