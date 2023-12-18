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
	private String Id;
		

	public MusicLike() {
		super();
	}

	public MusicLike(Long likeSeq, Long trackId, String id) {
		super();
		this.likeSeq = likeSeq;
		this.trackId = trackId;
		Id = id;
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

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
	
	
	
}
