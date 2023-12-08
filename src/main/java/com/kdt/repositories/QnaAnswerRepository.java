package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.QnaAnswer;

public interface QnaAnswerRepository extends JpaRepository<QnaAnswer, Long>{

	@Query("select q from QnaAnswer q where q.qnaSeq = ?1%")
	List<QnaAnswer> selectAllByParentSeq(Long seq);
}
