package com.kdt.dto;

public class SanctionViewDTO {

	
	private Long trackId;	
	private String title;
	private String writer;
	private String writeId;
	private Long ban;
	private String banReason;	
	private Long count;
	private String reportCategory;
	
	public SanctionViewDTO(Long trackId, String title, String writer, String writeId, Long ban, String banReason,
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

	public Long getBan() {
		return ban;
	}

	public void setBan(Long ban) {
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

	public SanctionViewDTO() {
	}

}
