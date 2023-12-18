package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.ReportAnswer;

public interface ReportAnswerRepository extends JpaRepository<ReportAnswer, Long>{

	@Query("select r from ReportAnswer r where r.reportSeq = ?1%")
	List<ReportAnswer> selectAllByParentSeq(Long seq);
	
}
