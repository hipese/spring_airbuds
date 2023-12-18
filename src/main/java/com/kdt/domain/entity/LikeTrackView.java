package com.kdt.domain.entity;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Immutable
public class LikeTrackView {

	@Id
	@Column(name = "track_id" )
	private Long trackId;
	
	@Column(name = "write_id")
	private String writeId;
	
	@Column(name = "count")
	private Long count;

	public LikeTrackView() {
		super();
	}

	public LikeTrackView(Long trackId, String writeId, Long count) {
		super();
		this.trackId = trackId;
		this.writeId = writeId;
		this.count = count;
	}

	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	public String getWriteId() {
		return writeId;
	}

	public void setWriteId(String writeId) {
		this.writeId = writeId;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
}
