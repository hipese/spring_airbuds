package com.kdt.domain.entity;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Immutable
public class SanctionView {

	@Id
	@Column(name = "track_id" )
	private Long trackId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="writer")
	private String writer;
	
	@Column(name="write_id")
	private String writeId;

	@Column(name = "ban")
	private String ban;

	@Column(name = "ban_reason")
	private String banReason;

	@Column(name = "count")
	private Long count;
	
	@Column(name = "report_category")
	private String reportCategory;

	public SanctionView(Long trackId, String title, String writer, String writeId, String ban, String banReason,
			Long count, String reportCategory) {
		super();
		this.trackId = trackId;
		this.title = title;
		this.writer = writer;
		this.writeId = writeId;
		this.ban = ban;
		this.banReason = banReason;
		this.count = count;
		this.reportCategory = reportCategory;
	}

	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriteId() {
		return writeId;
	}

	public void setWriteId(String writeId) {
		this.writeId = writeId;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getBanReason() {
		return banReason;
	}

	public void setBanReason(String banReason) {
		this.banReason = banReason;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getReportCategory() {
		return reportCategory;
	}

	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory;
	}

	public SanctionView() {
	}

}
