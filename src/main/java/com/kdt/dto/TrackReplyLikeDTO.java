package com.kdt.dto;

public class TrackReplyLikeDTO {

	private Long seq;
	private Long replySeq;
	private String id;
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public Long getReplySeq() {
		return replySeq;
	}
	public void setReplySeq(Long replySeq) {
		this.replySeq = replySeq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TrackReplyLikeDTO(Long seq, Long replySeq, String id) {
		super();
		this.seq = seq;
		this.replySeq = replySeq;
		this.id = id;
	}
	public TrackReplyLikeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
