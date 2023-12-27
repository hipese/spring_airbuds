package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.Qna;

public interface QnaRepository extends JpaRepository<Qna, Long>{

	@Query("select q from Qna q left join fetch q.files order by q.qnaSeq desc")
	List<Qna> findAllByFetchJoin();
}