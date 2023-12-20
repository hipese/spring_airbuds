package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.MemberAgeView;

public interface MemberAgeViewRepository extends JpaRepository<MemberAgeView, String>{

}
