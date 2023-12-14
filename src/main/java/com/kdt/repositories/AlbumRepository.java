package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.kdt.domain.entity.Album;


public interface AlbumRepository extends JpaRepository<Album, Long> {

}
