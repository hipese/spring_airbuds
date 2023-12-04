package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
