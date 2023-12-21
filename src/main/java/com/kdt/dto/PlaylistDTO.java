package com.kdt.dto;

import java.util.List;

public class PlaylistDTO {
	private Long playlistSeq;
	private String playlistPlTitle;
    private String playlistWriteId;
	private String playlistVisibility;
	private List<PlaylistTrackDTO> playlistTracks;
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
	public List<PlaylistTrackDTO> getPlaylistTracks() {
		return playlistTracks;
	}
	public void setPlaylistTracks(List<PlaylistTrackDTO> playlistTracks) {
		this.playlistTracks = playlistTracks;
	}
	public PlaylistDTO(Long playlistSeq, String playlistPlTitle, String playlistWriteId, String playlistVisibility,
			List<PlaylistTrackDTO> playlistTracks) {
		this.playlistSeq = playlistSeq;
		this.playlistPlTitle = playlistPlTitle;
		this.playlistWriteId = playlistWriteId;
		this.playlistVisibility = playlistVisibility;
		this.playlistTracks = playlistTracks;
	}
	public PlaylistDTO() {
	}
	
}
