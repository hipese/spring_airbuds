package com.kdt.domain.entity;

import java.sql.Time;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

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
	
	@Column(name="view_count")
	private Long viewCount;
	
	@Column(name="writer")
	private String writer;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="track_id")
	private Set<TrackImages> trackImages;
	

	public Track() {
		super();
	}

	public Track(Long trackId, Long albumId, String title, Long trackNumber, Time duration, String filePath,
			Long viewCount, String writer, Set<TrackImages> trackImages) {
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

	public Set<TrackImages> getTrackImages() {
		return trackImages;
	}

	public void setTrackImages(Set<TrackImages> trackImages) {
		this.trackImages = trackImages;
	}

	
	
}
