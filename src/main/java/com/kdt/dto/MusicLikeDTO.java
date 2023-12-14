package com.kdt.dto;

public class MusicLikeDTO {

	private Long likeSeq;
	private String Id;
	private Long trackId;
	public MusicLikeDTO() {
		super();
	}
	public MusicLikeDTO(Long likeSeq, String id, Long trackId) {
		super();
		this.likeSeq = likeSeq;
		Id = id;
		this.trackId = trackId;
	}
	public Long getLikeSeq() {
		return likeSeq;
	}
	public void setLikeSeq(Long likeSeq) {
		this.likeSeq = likeSeq;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Long getTrackId() {
		return trackId;
	}
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}
	
	
}
