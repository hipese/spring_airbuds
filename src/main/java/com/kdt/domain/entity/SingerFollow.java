package com.kdt.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SingerFollow {

	@Id
	@Column(name = "follow_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long followSeq;
	
	@Column(name = "member_id")
	private String memberId;
	
	@Column(name = "singer_id")
	private String singerId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Member member;

	public SingerFollow() {
		super();
	}


	public SingerFollow(Long followSeq, String memberId, String singerId, Member member) {
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


	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}
	
	
}
