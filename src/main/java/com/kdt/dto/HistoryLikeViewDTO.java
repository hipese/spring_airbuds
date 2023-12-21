package com.kdt.dto;

public class HistoryLikeViewDTO {

	private Long seq;
	private String memberId;
	private Long trackId;
	private Long likeCount;
	public HistoryLikeViewDTO() {
		super();
	}
	public HistoryLikeViewDTO(Long seq, String memberId, Long trackId, Long likeCount) {
		super();
		this.seq = seq;
		this.memberId = memberId;
		this.trackId = trackId;
		this.likeCount = likeCount;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Long getTrackId() {
		return trackId;
	}
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}
	public Long getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}
	
}
