package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.TrackTag;

public interface TrackTagRepository extends JpaRepository<TrackTag, Long> {

}
