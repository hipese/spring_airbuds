package com.kdt.dto;

import java.util.Set;

import com.kdt.domain.entity.Track;
import com.kdt.domain.entity.TrackImages;

public class TrackTagDTO {

	private Long tagId;
	private Long trackId;
	private String tag;
	private Set<Track> track;
	private Set<TrackImages> trackImages;
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
	public Set<Track> getTrack() {
		return track;
	}
	public void setTrack(Set<Track> track) {
		this.track = track;
	}
	public Set<TrackImages> getTrackImages() {
		return trackImages;
	}
	public void setTrackImages(Set<TrackImages> trackImages) {
		this.trackImages = trackImages;
	}
	public TrackTagDTO(Long tagId, Long trackId, String tag, Set<Track> track, Set<TrackImages> trackImages) {
		super();
		this.tagId = tagId;
		this.trackId = trackId;
		this.tag = tag;
		this.track = track;
		this.trackImages = trackImages;
	}
	public TrackTagDTO() {
		super();
	}
	
	
	
	
}
