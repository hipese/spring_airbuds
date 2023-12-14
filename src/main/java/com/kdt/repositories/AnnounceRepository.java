package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.AnnounceBoard;

public interface AnnounceRepository extends JpaRepository<AnnounceBoard, Long>{

}
