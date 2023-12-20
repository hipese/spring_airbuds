package com.kdt.domain.entity;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Immutable
public class MyMusicLikes {

	@Column(name = "id")
	private String id;
	
	@Id
	@Column(name = "track_id")
	private Long trackId;
	
	@Column(name = "count")
	private Long count;

	public MyMusicLikes() {
		super();
	}

	public MyMusicLikes(String id, Long trackId, Long count) {
		super();
		this.id = id;
		this.trackId = trackId;
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	
	
}
