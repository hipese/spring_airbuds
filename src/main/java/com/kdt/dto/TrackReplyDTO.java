package com.kdt.dto;

import java.time.Instant;

public class TrackReplyDTO {

	private Long seq;
	private String writer;
	private String contents;
	private Instant writeDate;
	private Long trackId;
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Instant getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Instant writeDate) {
		this.writeDate = writeDate;
	}
	public Long getTrackId() {
		return trackId;
	}
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}
	public TrackReplyDTO(Long seq, String writer, String contents, Instant writeDate, Long trackId) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.contents = contents;
		this.writeDate = writeDate;
		this.trackId = trackId;
	}
	public TrackReplyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
