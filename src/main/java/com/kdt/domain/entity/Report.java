package com.kdt.domain.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Report {

	@Id
	@Column(name = "report_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reportSeq;
	
	@Column(name = "report_title")
	private String reportTitle;
	
	@Column(name = "report_contents")
	private String reportContents;
	
	@Column(name = "report_writer")
	private String reportWriter;
	
	@Column(name = "report_subject")
	private String reportSubject;

	@Column(name = "report_category")
	private String reportCategory;
	
	@Column(name = "report_write_date")
	private Timestamp reportWriteDate;
	
	@Column(name = "report_answer_state")
	private Long reportAnswerState;

	public Report() {
	}

	public Report(Long reportSeq, String reportTitle, String reportContents, String reportWriter, String reportSubject,
			String reportCategory, Timestamp reportWriteDate, Long reportAnswerState) {
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

	public Timestamp getReportWriteDate() {
		return reportWriteDate;
	}

	public void setReportWriteDate(Timestamp reportWriteDate) {
		this.reportWriteDate = reportWriteDate;
	}

	public Long getReportAnswerState() {
		return reportAnswerState;
	}

	public void setReportAnswerState(Long reportAnswerState) {
		this.reportAnswerState = reportAnswerState;
	}
	
}
