package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long>{

}
