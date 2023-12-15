package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.AlbumTag;

public interface AlbumTagRepository extends JpaRepository<AlbumTag, Long>{

}
