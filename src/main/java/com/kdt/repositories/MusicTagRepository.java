package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.MusicTags;

public interface MusicTagRepository extends JpaRepository<MusicTags, Long> {

}
