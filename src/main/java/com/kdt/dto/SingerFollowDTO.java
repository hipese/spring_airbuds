package com.kdt.dto;

public class SingerFollowDTO {

	private Long followSeq;
	private String memberId;
	private String singerId;
	public SingerFollowDTO() {
		super();
	}
	public SingerFollowDTO(Long followSeq, String memberId, String singerId) {
		super();
		this.followSeq = followSeq;
		this.memberId = memberId;
		this.singerId = singerId;
	}
	public Long getFollowSeq() {
		return followSeq;
	}
	public void setFollowSeq(Long followSeq) {
		this.followSeq = followSeq;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getSingerId() {
		return singerId;
	}
	public void setSingerId(String singerId) {
		this.singerId = singerId;
	}	
	
}
