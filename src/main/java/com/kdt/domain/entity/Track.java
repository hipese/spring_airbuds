package com.kdt.domain.entity;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Track {

	@Id
	@Column(name="track_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long trackId;
	
	@Column(name="album_id")
	private Long albumId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="trackNumber")
	private Long trackNumber;
	
	@Column(name="duration")
	private Time duration;
	
	@Column(name="filePath")
	private String filePath;
	
	@Column(name="image_path")
	private String imagePath;
	
	@Column(name="view_count")
	private String viewCount;

	
	public Track() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Track(Long trackId, Long albumId, String title, Long trackNumber, Time duration, String filePath,
			String imagePath, String viewCount) {
		super();
		this.trackId = trackId;
		this.albumId = albumId;
		this.title = title;
		this.trackNumber = trackNumber;
		this.duration = duration;
		this.filePath = filePath;
		this.imagePath = imagePath;
		this.viewCount = viewCount;
	}

	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(Long trackNumber) {
		this.trackNumber = trackNumber;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getViewCount() {
		return viewCount;
	}

	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}
	

	
	
}
