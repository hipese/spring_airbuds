package com.kdt.dto;

import java.util.Set;

import com.kdt.domain.entity.Track;

public class MusicLikeDTO {

	private Long likeSeq;
	private String userId;
	private Long trackId;
	public Long getLikeSeq() {
		return likeSeq;
	}
	public void setLikeSeq(Long likeSeq) {
		this.likeSeq = likeSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getTrackId() {
		return trackId;
	}
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}
	public MusicLikeDTO(Long likeSeq, String userId, Long trackId) {
		this.likeSeq = likeSeq;
		this.userId = userId;
		this.trackId = trackId;
	}
	public MusicLikeDTO() {
	}
	
	
}
