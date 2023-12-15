package com.kdt.domain.entity;

import java.util.List;
import java.util.Set;

import com.kdt.dto.PlaylistTrackDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Playlist {

	@Id
	@Column(name="playlist_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playlistSeq;
	
	@Column(name="playlist_pl_title")
	private String playlistPlTitle;
	
	@Column(name="playlist_write_id")
	private String playlistWriteId;
	
	@Column(name="playlist_visibility")
	private String playlistVisibility;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="playlist_parent_seq")
	private Set<PlaylistTrack> playlistTracks;

	public Long getPlaylistSeq() {
		return playlistSeq;
	}

	public void setPlaylistSeq(Long playlistSeq) {
		this.playlistSeq = playlistSeq;
	}

	public String getPlaylistPlTitle() {
		return playlistPlTitle;
	}

	public void setPlaylistPlTitle(String playlistPlTitle) {
		this.playlistPlTitle = playlistPlTitle;
	}

	public String getPlaylistWriteId() {
		return playlistWriteId;
	}

	public void setPlaylistWriteId(String playlistWriteId) {
		this.playlistWriteId = playlistWriteId;
	}

	public String getPlaylistVisibility() {
		return playlistVisibility;
	}

	public void setPlaylistVisibility(String playlistVisibility) {
		this.playlistVisibility = playlistVisibility;
	}

	public Set<PlaylistTrack> getPlaylistTracks() {
		return playlistTracks;
	}

	public void setPlaylistTracks(Set<PlaylistTrack> playlistTracks) {
		this.playlistTracks = playlistTracks;
	}

	public Playlist(Long playlistSeq, String playlistPlTitle, String playlistWriteId, String playlistVisibility,
			Set<PlaylistTrack> playlistTracks) {
		this.playlistSeq = playlistSeq;
		this.playlistPlTitle = playlistPlTitle;
		this.playlistWriteId = playlistWriteId;
		this.playlistVisibility = playlistVisibility;
		this.playlistTracks = playlistTracks;
	}

	public Playlist() {
	} 
	
}
