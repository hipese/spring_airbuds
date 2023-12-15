package com.kdt.domain.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ReportAnswer {

	@Id
	@Column(name = "report_answer_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reportAnswerSeq;
	
	@Column(name = "report_seq")
	private Long reportSeq;
	
	@Column(name = "report_answer_contents")
	private String reportAnswerContents;
	
	@Column(name = "report_answer_writer")
	private String reportAnswerWriter;
	
	@Column(name = "report_answer_write_date")
	private Timestamp reportAnswerWriteDate;

	public ReportAnswer() {
	}

	public ReportAnswer(Long reportAnswerSeq, Long reportSeq, String reportAnswerContents, String reportAnswerWriter,
			Timestamp reportAnswerWriteDate) {
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

	public Timestamp getReportAnswerWriteDate() {
		return reportAnswerWriteDate;
	}

	public void setReportAnswerWriteDate(Timestamp reportAnswerWriteDate) {
		this.reportAnswerWriteDate = reportAnswerWriteDate;
	}
	
}
