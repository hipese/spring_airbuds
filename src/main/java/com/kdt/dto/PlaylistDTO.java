package com.kdt.dto;

import java.util.List;

public class PlaylistDTO {
	private Long playlistSeq;
	private String playlistPlTitle;
    private String playlistWriteId;
	private String playlistVisibility;
	private List<PlaylistTrackDTO> playlistTrack;
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
	public List<PlaylistTrackDTO> getPlaylistTrack() {
		return playlistTrack;
	}
	public void setPlaylistTrack(List<PlaylistTrackDTO> playlistTrack) {
		this.playlistTrack = playlistTrack;
	}
	public PlaylistDTO(Long playlistSeq, String playlistPlTitle, String playlistWriteId, String playlistVisibility,
			List<PlaylistTrackDTO> playlistTrack) {
		this.playlistSeq = playlistSeq;
		this.playlistPlTitle = playlistPlTitle;
		this.playlistWriteId = playlistWriteId;
		this.playlistVisibility = playlistVisibility;
		this.playlistTrack = playlistTrack;
	}
	public PlaylistDTO() {
	}
}
