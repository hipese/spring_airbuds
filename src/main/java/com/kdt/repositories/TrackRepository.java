package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.kdt.domain.entity.Track;

public interface TrackRepository extends JpaRepository<Track, Long> {
	
	List<Track> findAllByWriterStartingWith(String empName); //Quiz 1
}
