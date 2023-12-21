package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.kdt.domain.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
	List<Playlist> findByPlaylistWriteId(String playlistWriteId);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM PlaylistTrack pt WHERE pt.playlistParentSeq = ?1")
    void deletePlaylistTracksByPlaylistParentSeq(Long playlistSeq);
}
