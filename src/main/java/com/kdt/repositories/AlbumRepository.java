package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kdt.domain.entity.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

}
