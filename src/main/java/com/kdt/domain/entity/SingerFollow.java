package com.kdt.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

	public SingerFollow() {
		super();
	}

	public SingerFollow(Long followSeq, String memberId, String singerId) {
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
