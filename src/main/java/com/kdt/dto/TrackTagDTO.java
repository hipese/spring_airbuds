package com.kdt.dto;

public class TrackTagDTO {

	private Long tagId;
	private Long trackId;
	private String tag;
	
	public TrackTagDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrackTagDTO(Long tagId, Long trackId, String tag) {
		super();
		this.tagId = tagId;
		this.trackId = trackId;
		this.tag = tag;
	}
	
	public TrackTagDTO(Long trackId) {
		super();
		this.trackId = trackId;
	}
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	public Long getTrackId() {
		return trackId;
	}
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
