package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.AnnounceBoard;

public interface AnnounceRepository extends JpaRepository<AnnounceBoard, Long>{
	
	@Query("select a from AnnounceBoard a order by a.announceSeq Desc")
	List<AnnounceBoard> selectAllByDesc();
}
