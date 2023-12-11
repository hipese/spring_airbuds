package com.kdt.domain.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
	@JoinTable(
	    name="track_track_tag",
	    joinColumns = @JoinColumn(name="tag_id"), // track의 고유 Primary Key값
	    inverseJoinColumns = @JoinColumn(name="track_id") // 내 Primary Key
	)
	private Set<Track> track = new HashSet<>();

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

	public TrackTag(Long tagId, Long trackId, String tag, Set<Track> track) {
		super();
		this.tagId = tagId;
		this.trackId = trackId;
		this.tag = tag;
		this.track = track;
	}

	public TrackTag() {
		super();
	}
	
	
}
