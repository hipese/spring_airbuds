package com.kdt.dto;

public class MusicLikeDTO {

	private Long likeSeq;
	private String userId;
	private Long trackId;
	public MusicLikeDTO() {
		super();
	}
	public MusicLikeDTO(Long likeSeq, String userId, Long trackId) {
		super();
		this.likeSeq = likeSeq;
		this.userId = userId;
		this.trackId = trackId;
	}
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
	
	
}
