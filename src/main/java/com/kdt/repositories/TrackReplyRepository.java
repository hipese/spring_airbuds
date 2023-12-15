package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.TrackReply;

public interface TrackReplyRepository extends JpaRepository<TrackReply, Long>{

	@Query("select q from TrackReply q where q.trackId = ?1")
	List<TrackReply> selectAllByParentSeq(Long seq);
}
