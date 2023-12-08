package com.kdt.dto;

import java.sql.Time;
import java.util.Set;

public class TrackDTO {
	private Long trackId;
	private Long albumId;
	private String title;
	private Long trackNumber;
	private Time duration;
	private String filePath;
	private Long viewCount;
	private String writer;
	private Set<TrackImageDTO> trackImages;
	
	public TrackDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrackDTO(Long trackId, Long albumId, String title, Long trackNumber, Time duration, String filePath,
			Long viewCount, String writer, Set<TrackImageDTO> trackImages) {
		super();
		this.trackId = trackId;
		this.albumId = albumId;
		this.title = title;
		this.trackNumber = trackNumber;
		this.duration = duration;
		this.filePath = filePath;
		this.viewCount = viewCount;
		this.writer = writer;
		this.trackImages = trackImages;
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
	public Long getViewCount() {
		return viewCount;
	}
	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Set<TrackImageDTO> getTrackImages() {
		return trackImages;
	}
	public void setTrackImages(Set<TrackImageDTO> trackImages) {
		this.trackImages = trackImages;
	}
	
	
}
