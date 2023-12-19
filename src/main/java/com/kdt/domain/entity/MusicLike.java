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
public class MusicLike {
	
	@Id
	@Column(name = "like_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long likeSeq;
	
	@Column(name = "track_id")
	private Long trackId;
	
	@Column(name = "id")
	private String userId;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="track_id", referencedColumnName = "track_id")
	private Set<Track> tracks;

	public Long getLikeSeq() {
		return likeSeq;
	}

	public void setLikeSeq(Long likeSeq) {
		this.likeSeq = likeSeq;
	}

	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Set<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

	public MusicLike(Long likeSeq, Long trackId, String userId, Set<Track> tracks) {
		super();
		this.likeSeq = likeSeq;
		this.trackId = trackId;
		this.userId = userId;
		this.tracks = tracks;
	}

	public MusicLike() {
		super();
		// TODO Auto-generated constructor stub
	}


}
