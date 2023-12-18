package com.kdt.dto;

import java.sql.Time;

public class PlaylistTrackDTO {
	private Long playlistSeq;
	private Long playlistTrackId;
    private String playlistTitle;
    private String playlistImagePath;
    private Time playlistDuration;
    private String playlistFilePath;
    private String playlistWriter;
    private Long playlistParentSeq;
	public Long getPlaylistSeq() {
		return playlistSeq;
	}
	public void setPlaylistSeq(Long playlistSeq) {
		this.playlistSeq = playlistSeq;
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
	public String getPlaylistWriter() {
		return playlistWriter;
	}
	public void setPlaylistWriter(String playlistWriter) {
		this.playlistWriter = playlistWriter;
	}
	public Long getPlaylistParentSeq() {
		return playlistParentSeq;
	}
	public void setPlaylistParentSeq(Long playlistParentSeq) {
		this.playlistParentSeq = playlistParentSeq;
	}
	public PlaylistTrackDTO(Long playlistSeq, Long playlistTrackId, String playlistTitle, String playlistImagePath,
			Time playlistDuration, String playlistFilePath, String playlistWriter, Long playlistParentSeq) {
		this.playlistSeq = playlistSeq;
		this.playlistTrackId = playlistTrackId;
		this.playlistTitle = playlistTitle;
		this.playlistImagePath = playlistImagePath;
		this.playlistDuration = playlistDuration;
		this.playlistFilePath = playlistFilePath;
		this.playlistWriter = playlistWriter;
		this.playlistParentSeq = playlistParentSeq;
	}
	public PlaylistTrackDTO() {
	}
}
