package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long>{
	
}
