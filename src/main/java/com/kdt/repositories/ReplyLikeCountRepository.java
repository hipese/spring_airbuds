package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.ReplyLikeCount;

public interface ReplyLikeCountRepository extends JpaRepository<ReplyLikeCount, Long>{

	@Query("select q from ReplyLikeCount q where q.trackId = ?1 ORDER BY q.seq DESC")
	List<ReplyLikeCount> selectAllByParentSeq(Long seq);
}
