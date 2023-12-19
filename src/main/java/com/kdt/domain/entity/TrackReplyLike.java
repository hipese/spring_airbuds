package com.kdt.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TrackReplyLike {

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@Column(name = "reply_seq")
	private Long replySeq;
	
	@Column(name = "id")
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

	public TrackReplyLike(Long seq, Long replySeq, String id) {
		super();
		this.seq = seq;
		this.replySeq = replySeq;
		this.id = id;
	}

	public TrackReplyLike() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
