package com.kdt.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

	public MusicLike() {
		super();
	}

	public MusicLike(Long likeSeq, Long trackId, String userId) {
		super();
		this.likeSeq = likeSeq;
		this.trackId = trackId;
		this.userId = userId;
	}

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

	
}
