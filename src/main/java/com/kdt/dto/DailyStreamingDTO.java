package com.kdt.dto;

import java.time.Instant;

public class DailyStreamingDTO {

	private Long streamSeq;
	private Long trackId;
	private String streamSinger;
	private Instant streamDate;
	private Long streamCount;
	public DailyStreamingDTO() {
		super();
	}
	public DailyStreamingDTO(Long streamSeq, Long trackId, String streamSinger, Instant streamDate, Long streamCount) {
		super();
		this.streamSeq = streamSeq;
		this.trackId = trackId;
		this.streamSinger = streamSinger;
		this.streamDate = streamDate;
		this.streamCount = streamCount;
	}
	public Long getStreamSeq() {
		return streamSeq;
	}
	public void setStreamSeq(Long streamSeq) {
		this.streamSeq = streamSeq;
	}
	public Long getTrackId() {
		return trackId;
	}
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}
	public String getStreamSinger() {
		return streamSinger;
	}
	public void setStreamSinger(String streamSinger) {
		this.streamSinger = streamSinger;
	}
	public Instant getStreamDate() {
		return streamDate;
	}
	public void setStreamDate(Instant streamDate) {
		this.streamDate = streamDate;
	}
	public Long getStreamCount() {
		return streamCount;
	}
	public void setStreamCount(Long streamCount) {
		this.streamCount = streamCount;
	}
	
	
}
