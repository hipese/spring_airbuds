package com.kdt.domain.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class TrackTag {
	
	@Id
	@Column(name="tag_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tagId ;
	
	@Column(name="track_id")
	private Long trackId;
	
	@Column(name="tag")
	private String tag;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="track_id")
	private Set<Track> track;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="track_id", referencedColumnName = "track_id")
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


	public TrackTag(Long tagId, Long trackId, String tag, Set<Track> track, Set<TrackImages> trackImages) {
		super();
		this.tagId = tagId;
		this.trackId = trackId;
		this.tag = tag;
		this.track = track;
		this.trackImages = trackImages;
	}


	public TrackTag() {
		super();
	}
	
	

	
}
