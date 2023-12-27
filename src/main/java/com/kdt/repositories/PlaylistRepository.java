package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
	@EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = "playlistTracks")
    List<Playlist> findByPlaylistWriteId(String playlistWriteId);
	
	@Modifying
	@Query("DELETE FROM PlaylistTrack pt WHERE pt.playlist.playlistSeq = ?1")
    void deletePlaylistTracksByPlaylistParentSeq(Long playlistSeq);
}
