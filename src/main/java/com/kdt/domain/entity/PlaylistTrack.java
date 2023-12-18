package com.kdt.domain.entity;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PlaylistTrack {
	@Id
	@Column(name="playlist_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playlistSeq;
	
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
	
	@Column(name="playlist_writer")
	private String playlistWriter;

	@Column(name="playlist_parent_seq")
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

	public PlaylistTrack(Long playlistSeq, Long playlistTrackId, String playlistTitle, String playlistImagePath,
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

	public PlaylistTrack() {
	}

	
}
