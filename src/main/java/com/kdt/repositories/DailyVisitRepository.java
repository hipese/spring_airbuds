package com.kdt.repositories;

import java.sql.Timestamp;
import java.time.Instant;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.DailyVisit;

public interface DailyVisitRepository extends JpaRepository<DailyVisit, Long>{

	@Query("select dv from DailyVisit dv where function('DATE',dv.visitDate) = function('CURDATE')")
	DailyVisit selectVisitByDate();
	
	@Query("select dv.visitCount from DailyVisit dv where function('DATE',dv.visitDate) = function('CURDATE')")
	Long selectVisitCount();
	
}
