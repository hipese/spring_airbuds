package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.TrackReplyLike;

public interface TrackReplyLikeRepository extends JpaRepository<TrackReplyLike, Long> {

	@Query("select r from TrackReplyLike r where r.id = ?1%")
	List<TrackReplyLike> selectAllById(String id);

	@Query("select r from TrackReplyLike r where r.id = ?1% and r.replySeq = ?2%")
	List<TrackReplyLike> selectAllByIdAndReply(String id, Long replySeq);
}
