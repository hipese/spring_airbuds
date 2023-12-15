package com.kdt.dto;

import java.time.Instant;

public class ReportAnswerDTO {

	private Long reportAnswerSeq;
	private Long reportSeq;
	private String reportAnswerContents;
	private String reportAnswerWriter;
	private Instant reportAnswerWriteDate;
	
	public ReportAnswerDTO() {
	}
	
	public ReportAnswerDTO(Long reportAnswerSeq, Long reportSeq, String reportAnswerContents, String reportAnswerWriter,
			Instant reportAnswerWriteDate) {
		super();
		this.reportAnswerSeq = reportAnswerSeq;
		this.reportSeq = reportSeq;
		this.reportAnswerContents = reportAnswerContents;
		this.reportAnswerWriter = reportAnswerWriter;
		this.reportAnswerWriteDate = reportAnswerWriteDate;
	}
	
	public Long getReportAnswerSeq() {
		return reportAnswerSeq;
	}
	
	public void setReportAnswerSeq(Long reportAnswerSeq) {
		this.reportAnswerSeq = reportAnswerSeq;
	}
	
	public Long getReportSeq() {
		return reportSeq;
	}
	
	public void setReportSeq(Long reportSeq) {
		this.reportSeq = reportSeq;
	}
	
	public String getReportAnswerContents() {
		return reportAnswerContents;
	}
	
	public void setReportAnswerContents(String reportAnswerContents) {
		this.reportAnswerContents = reportAnswerContents;
	}
	
	public String getReportAnswerWriter() {
		return reportAnswerWriter;
	}
	
	public void setReportAnswerWriter(String reportAnswerWriter) {
		this.reportAnswerWriter = reportAnswerWriter;
	}
	
	public Instant getReportAnswerWriteDate() {
		return reportAnswerWriteDate;
	}
	
	public void setReportAnswerWriteDate(Instant reportAnswerWriteDate) {
		this.reportAnswerWriteDate = reportAnswerWriteDate;
	}

}
