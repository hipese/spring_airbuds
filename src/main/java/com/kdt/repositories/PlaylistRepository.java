package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
	List<Playlist> findByPlaylistWriteId(String playlistWriteId);
}
