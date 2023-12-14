package com.kdt.dto;

import java.sql.Time;
import java.util.Set;

public class PlaylistDTO {

	private Long playlistSeq;
	private String playlistPlTitle;
	private Long playlistTrackId;
    private String playlistTitle;
    private String playlistImagePath;
    private Time playlistDuration;
    private String playlistFilePath;
    private String playlistWriteId;
    private String playlistWriter;
	private String playlistVisibility;
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
	public Long getPlaylistTrackId() {
		return playlistTrackId;
	}
	public void setPlaylistTrackId(Long playlistTrackId) {
		this.playlistTrackId = playlistTrackId;
	}
	public String getPlaylistTitle() {
		return playlistTitle;
	}
	public void setPlaylistTitle(String playlistTitle) {
		this.playlistTitle = playlistTitle;
	}
	public String getPlaylistImagePath() {
		return playlistImagePath;
	}
	public void setPlaylistImagePath(String playlistImagePath) {
		this.playlistImagePath = playlistImagePath;
	}
	public Time getPlaylistDuration() {
		return playlistDuration;
	}
	public void setPlaylistDuration(Time playlistDuration) {
		this.playlistDuration = playlistDuration;
	}
	public String getPlaylistFilePath() {
		return playlistFilePath;
	}
	public void setPlaylistFilePath(String playlistFilePath) {
		this.playlistFilePath = playlistFilePath;
	}
	public String getPlaylistWriteId() {
		return playlistWriteId;
	}
	public void setPlaylistWriteId(String playlistWriteId) {
		this.playlistWriteId = playlistWriteId;
	}
	public String getPlaylistWriter() {
		return playlistWriter;
	}
	public void setPlaylistWriter(String playlistWriter) {
		this.playlistWriter = playlistWriter;
	}
	public String getPlaylistVisibility() {
		return playlistVisibility;
	}
	public void setPlaylistVisibility(String playlistVisibility) {
		this.playlistVisibility = playlistVisibility;
	}
	public PlaylistDTO(Long playlistSeq, String playlistPlTitle, Long playlistTrackId, String playlistTitle,
			String playlistImagePath, Time playlistDuration, String playlistFilePath, String playlistWriteId,
			String playlistWriter, String playlistVisibility) {
		this.playlistSeq = playlistSeq;
		this.playlistPlTitle = playlistPlTitle;
		this.playlistTrackId = playlistTrackId;
		this.playlistTitle = playlistTitle;
		this.playlistImagePath = playlistImagePath;
		this.playlistDuration = playlistDuration;
		this.playlistFilePath = playlistFilePath;
		this.playlistWriteId = playlistWriteId;
		this.playlistWriter = playlistWriter;
		this.playlistVisibility = playlistVisibility;
	}
	public PlaylistDTO() {
	}
	
	
}
