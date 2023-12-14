package com.kdt.domain.entity;

import java.sql.Time;
import java.util.Set;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Playlist {

	@Id
	@Column(name="playlist_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playlistSeq;
	
	@Column(name="playlist_pl_title")
	private String playlistPlTitle;
	
	@Column(name="playlist_track_id")
	private Long playlistTrackId;
	
	@Column(name="playlist_title")
	private String playlistTitle;
	
	@Column(name="playlist_image_path")
	private String playlistImagePath;
	
	@Column(name="playlist_duration")
	private Time playlistDuration;
	
	@Column(name="playlist_file_path")
	private String playlistFilePath;
	
	@Column(name="playlist_write_id")
	private String playlistWriteId;
	
	@Column(name="playlist_writer")
	private String playlistWriter;
	
	@Column(name="playlist_visibility")
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

	public Playlist(Long playlistSeq, String playlistPlTitle, Long playlistTrackId, String playlistTitle,
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

	public Playlist() {
	}

	
}
