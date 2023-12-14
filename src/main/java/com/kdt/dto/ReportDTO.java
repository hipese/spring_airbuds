package com.kdt.dto;

import java.time.Instant;

public class ReportDTO {

	private Long reportSeq;
	private String reportTitle;
	private String reportContents;
	private String reportWriter;
	private String reportSubject;
	private String reportCategory;
	private Instant reportWriteDate;
	private Long reportAnswerState;
	
	public ReportDTO() {
	}
	
	public ReportDTO(Long reportSeq, String reportTitle, String reportContents, String reportWriter,
			String reportSubject, String reportCategory, Instant reportWriteDate, Long reportAnswerState) {
		super();
		this.reportSeq = reportSeq;
		this.reportTitle = reportTitle;
		this.reportContents = reportContents;
		this.reportWriter = reportWriter;
		this.reportSubject = reportSubject;
		this.reportCategory = reportCategory;
		this.reportWriteDate = reportWriteDate;
		this.reportAnswerState = reportAnswerState;
	}
	
	public Long getReportSeq() {
		return reportSeq;
	}
	public void setReportSeq(Long reportSeq) {
		this.reportSeq = reportSeq;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public String getReportContents() {
		return reportContents;
	}
	public void setReportContents(String reportContents) {
		this.reportContents = reportContents;
	}
	public String getReportWriter() {
		return reportWriter;
	}
	public void setReportWriter(String reportWriter) {
		this.reportWriter = reportWriter;
	}
	public String getReportSubject() {
		return reportSubject;
	}
	public void setReportSubject(String reportSubject) {
		this.reportSubject = reportSubject;
	}
	public String getReportCategory() {
		return reportCategory;
	}
	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory;
	}
	public Instant getReportWriteDate() {
		return reportWriteDate;
	}
	public void setReportWriteDate(Instant reportWriteDate) {
		this.reportWriteDate = reportWriteDate;
	}
	public Long getReportAnswerState() {
		return reportAnswerState;
	}
	public void setReportAnswerState(Long reportAnswerState) {
		this.reportAnswerState = reportAnswerState;
	}
	
}
