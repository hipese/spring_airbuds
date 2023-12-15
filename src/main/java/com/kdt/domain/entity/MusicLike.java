package com.kdt.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MusicLike {
	
	@Id
	@Column(name = "like_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long likeSeq;
	
	@Column(name = "track_id")
	private Long trackId;
	
	@Column(name = "id")
	private String id;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MusicLike(Long likeSeq, Long trackId, String id) {
		this.likeSeq = likeSeq;
		this.trackId = trackId;
		this.id = id;
	}

	public MusicLike() {
	}
	
	
}
