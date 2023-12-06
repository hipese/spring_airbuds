package com.kdt.dto;

import java.sql.Time;

public class TrackDTO {
	private Long trackId;
	private Long albumId;
	private String title;
	private Long trackNumber;
	private Time duration;
	private String filePath;
	private String imagePath;
	private String viewCount;
	
	public TrackDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrackDTO(Long trackId, Long albumId, String title, Long trackNumber, Time duration, String filePath,
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
