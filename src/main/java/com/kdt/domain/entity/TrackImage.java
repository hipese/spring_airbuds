package com.kdt.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TrackImage {
	
	@Id
	@Column(name="image_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imageId;
	
	@Column(name="track_id")
	private Long trackId;
	
	@Column(name="image_path")
	private String imagePath;

	
	public TrackImage(Long imageId, Long trackId, String imagePath) {
		super();
		this.imageId = imageId;
		this.trackId = trackId;
		this.imagePath = imagePath;
	}

	public TrackImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
	
}
