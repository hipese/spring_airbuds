package com.kdt.domain.entity;

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
public class CurrentPlayList {
	
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@Column(name="track_id")
	private Long trackId;
	
	@Column(name="id")
	private String id;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="track_id", referencedColumnName = "track_id")
	private Set<Track> tracks;

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

	public CurrentPlayList(Long seq, Long trackId, String id, Set<Track> tracks) {
		super();
		this.seq = seq;
		this.trackId = trackId;
		this.id = id;
		this.tracks = tracks;
	}

	public CurrentPlayList() {
		super();
		// TODO Auto-generated constructor stub
	}



	
}
