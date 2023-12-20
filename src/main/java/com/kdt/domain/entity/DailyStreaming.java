package com.kdt.domain.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DailyStreaming {

	@Id
	@Column(name = "stream_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long streamSeq;
	
	@Column(name = "track_id")
	private Long trackId;
	
	@Column(name = "stream_singer")
	private String streamSinger;
	
	@Column(name = "stream_date")
	private Timestamp streamDate;
	
	@Column(name = "stream_count")
	private Long streamCount;

	public DailyStreaming() {
		super();
	}

	public DailyStreaming(Long streamSeq, Long trackId, String streamSinger, Timestamp streamDate, Long streamCount) {
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

	public Timestamp getStreamDate() {
		return streamDate;
	}

	public void setStreamDate(Timestamp streamDate) {
		this.streamDate = streamDate;
	}

	public Long getStreamCount() {
		return streamCount;
	}

	public void setStreamCount(Long streamCount) {
		this.streamCount = streamCount;
	}
	
}
