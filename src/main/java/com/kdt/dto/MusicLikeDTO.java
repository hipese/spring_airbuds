package com.kdt.dto;

import java.util.Set;

import com.kdt.domain.entity.Track;

public class MusicLikeDTO {

	private Long likeSeq;
	private String id;
	private Long trackId;
	public Long getLikeSeq() {
		return likeSeq;
	}
	public void setLikeSeq(Long likeSeq) {
		this.likeSeq = likeSeq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getTrackId() {
		return trackId;
	}
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}
	public MusicLikeDTO(Long likeSeq, String id, Long trackId) {
		this.likeSeq = likeSeq;
		this.id = id;
		this.trackId = trackId;
	}
	public MusicLikeDTO() {
	}
	
}
