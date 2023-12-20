package com.kdt.dto;

public class SingerFollowDTO {

	private Long followSeq;
	private String memberId;
	private String singerId;
	private MemberDTO member;
	
	public SingerFollowDTO() {
		super();
	}

	public SingerFollowDTO(Long followSeq, String memberId, String singerId, MemberDTO member) {
		super();
		this.followSeq = followSeq;
		this.memberId = memberId;
		this.singerId = singerId;
		this.member = member;
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

	public MemberDTO getMember() {
		return member;
	}

	public void setMember(MemberDTO member) {
		this.member = member;
	}		
	
}
