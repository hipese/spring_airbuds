package com.kdt.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TrackTag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="track_id")
	private Track track;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="tag_id")
	private MusicTags musicTags;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public MusicTags getMusicTags() {
		return musicTags;
	}

	public void setMusicTags(MusicTags musicTags) {
		this.musicTags = musicTags;
	}

	public TrackTag(Long id, Track track, MusicTags musicTags) {
		this.id = id;
		this.track = track;
		this.musicTags = musicTags;
	}

	public TrackTag() {
	}
	
	
}
