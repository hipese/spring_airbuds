package com.kdt.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long>{
	
	@Query("SELECT function('YEAR',r.reportWriteDate) AS year, function('MONTH',r.reportWriteDate) AS month, function('COUNT',r) AS count FROM Report r GROUP BY function('YEAR',r.reportWriteDate), function('MONTH',r.reportWriteDate) ORDER BY function('YEAR',r.reportWriteDate), function('MONTH',r.reportWriteDate)")
	List<Map<String, Object>> selectReportByMonth();
}
