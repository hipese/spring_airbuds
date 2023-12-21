package com.kdt.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.HistoryLikeView;

public interface HistoryLikeViewRepository extends JpaRepository<HistoryLikeView, Long>{

	@Query("select hlv from HistoryLikeView hlv where hlv.memberId = :id")
	List<HistoryLikeView> gethistoryLike(@Param("id") String id);
}
