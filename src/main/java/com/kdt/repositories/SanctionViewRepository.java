package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.SanctionView;

public interface SanctionViewRepository extends JpaRepository<SanctionView, Long>{

	List<SanctionView> findByBanTrue();
	
}
