package com.kdt.dto;

public class LikeTrackViewDTO {
	
	private Long trackId;
	
	private String writeId;
	
	private Long count;

	public LikeTrackViewDTO() {
		super();
	}

	public LikeTrackViewDTO(Long trackId, String writeId, Long count) {
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
