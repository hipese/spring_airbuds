package com.kdt.domain.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AnnounceBoard {
	
	@Id
	@Column(name = "announce_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long announceSeq;
	
	@Column(name = "announce_title")
	private String announceTitle;
	
	@Column(name = "announce_contents")
	private String announceContents;
	
	@Column(name = "announce_writer")
	private String announceWriter;
	
	@Column(name = "announce_write_date")
	private Timestamp announceWriteDate;
	
	@Column(name = "announce_view_count")
	private Long announceViewCount;
	
	@Column(name = "announce_category")
	private String announceCategory;

	public AnnounceBoard() {
		super();
	}

	public AnnounceBoard(Long announceSeq, String announceTitle, String announceContents, String announceWriter,
			Timestamp announceWriteDate, Long announceViewCount, String announceCategory) {
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

	public Timestamp getAnnounceWriteDate() {
		return announceWriteDate;
	}

	public void setAnnounceWriteDate(Timestamp announceWriteDate) {
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
