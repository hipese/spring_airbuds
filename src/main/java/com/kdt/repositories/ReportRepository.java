package com.kdt.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long>{
	
	// 신고 월별 데이터 산출
	@Query("SELECT function('YEAR',r.reportWriteDate) AS year, function('MONTH',r.reportWriteDate) AS month, function('COUNT',r) AS count FROM Report r GROUP BY function('YEAR',r.reportWriteDate), function('MONTH',r.reportWriteDate) ORDER BY function('YEAR',r.reportWriteDate), function('MONTH',r.reportWriteDate)")
	public List<Map<String, Object>> selectReportByMonth();
	
	
	@Query("SELECT function('COUNT', r) FROM Report r WHERE function('DAY',r.reportWriteDate) = function('DAY',function('CURDATE')) AND function('MONTH',r.reportWriteDate) = function('MONTH',function('CURDATE')) AND function('YEAR',r.reportWriteDate) = function('YEAR',function('CURDATE'))")
	public Long selectReportCountByDay();
}
