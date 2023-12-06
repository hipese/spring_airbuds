package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.Track;

public interface TrackRepository extends JpaRepository<Track, Long> {

}
