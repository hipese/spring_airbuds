package com.kdt.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TrackTag {
	
	@Id
	@Column(name="tag_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tagId ;
	
	@Column(name="track_id")
	private Long trackId;
	
	@Column(name="tag")
	private String tag;
	

	public TrackTag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrackTag(Long tagId, Long trackId, String tag) {
		super();
		this.tagId = tagId;
		this.trackId = trackId;
		this.tag = tag;
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
