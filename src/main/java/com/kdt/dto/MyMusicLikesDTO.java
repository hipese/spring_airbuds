package com.kdt.dto;

public class MyMusicLikesDTO {

	private String id;
	private Long trackId;
	private Long count;
	public MyMusicLikesDTO() {
		super();
	}
	public MyMusicLikesDTO(String id, Long trackId, Long count) {
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
