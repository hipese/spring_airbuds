package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.TrackImages;

public interface TrackImageRepository extends JpaRepository<TrackImages, Long> {

}
