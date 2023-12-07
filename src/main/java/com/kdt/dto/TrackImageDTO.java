package com.kdt.dto;

public class TrackImageDTO {
	private Long imageId;
	private Long trackId;
	private String imagePath;
	
	
	public TrackImageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrackImageDTO(Long imageId, Long trackId, String imagePath) {
		super();
		this.imageId = imageId;
		this.trackId = trackId;
		this.imagePath = imagePath;
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
