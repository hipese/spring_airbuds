package com.kdt.dto;

import java.time.Instant;

public class AnnounceBoardDTO {
	private Long announceSeq;
	private String announceTitle;
	private String announceContents;
	private String announceWriter;
	private Instant announceWriteDate;
	private Long announceViewCount;
	private String announceCategory;
	public AnnounceBoardDTO() {
		super();
	}
	public AnnounceBoardDTO(Long announceSeq, String announceTitle, String announceContents, String announceWriter,
			Instant announceWriteDate, Long announceViewCount, String announceCategory) {
		super();
		this.announceSeq = announceSeq;
		this.announceTitle = announceTitle;
		this.announceContents = announceContents;
		this.announceWriter = announceWriter;
		this.announceWriteDate = announceWriteDate;
		this.announceViewCount = announceViewCount;
		this.announceCategory = announceCategory;
	}
	public Long getAnnounceSeq() {
		return announceSeq;
	}
	public void setAnnounceSeq(Long announceSeq) {
		this.announceSeq = announceSeq;
	}
	public String getAnnounceTitle() {
		return announceTitle;
	}
	public void setAnnounceTitle(String announceTitle) {
		this.announceTitle = announceTitle;
	}
	public String getAnnounceContents() {
		return announceContents;
	}
	public void setAnnounceContents(String announceContents) {
		this.announceContents = announceContents;
	}
	public String getAnnounceWriter() {
		return announceWriter;
	}
	public void setAnnounceWriter(String announceWriter) {
		this.announceWriter = announceWriter;
	}
	public Instant getAnnounceWriteDate() {
		return announceWriteDate;
	}
	public void setAnnounceWriteDate(Instant announceWriteDate) {
		this.announceWriteDate = announceWriteDate;
	}
	public Long getAnnounceViewCount() {
		return announceViewCount;
	}
	public void setAnnounceViewCount(Long announceViewCount) {
		this.announceViewCount = announceViewCount;
	}
	public String getAnnounceCategory() {
		return announceCategory;
	}
	public void setAnnounceCategory(String announceCategory) {
		this.announceCategory = announceCategory;
	}
}
