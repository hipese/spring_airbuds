package com.kdt.domain.entity;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Immutable
public class HistoryLikeView {

	@Id
	@Column(name = "seq")
	private Long seq;
	
	@Column(name = "member_id")
	private String memberId;
	
	@Column(name = "track_id")
	private Long trackId;
	
	@Column(name = "like_count")
	private Long likeCount;

	public HistoryLikeView() {
		super();
	}

	public HistoryLikeView(Long seq, String memberId, Long trackId, Long likeCount) {
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
