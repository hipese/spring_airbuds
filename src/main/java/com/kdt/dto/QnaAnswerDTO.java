package com.kdt.dto;

import java.time.Instant;

public class QnaAnswerDTO {

	private Long answerSeq;
	private Long qnaSeq;
	private String answerContents;
	private String answerWriter;
	private Instant answerWriteDate;
	public QnaAnswerDTO() {
		super();
	}
	public QnaAnswerDTO(Long answerSeq, Long qnaSeq, String answerContents, String answerWriter,
			Instant answerWriteDate) {
		super();
		this.answerSeq = answerSeq;
		this.qnaSeq = qnaSeq;
		this.answerContents = answerContents;
		this.answerWriter = answerWriter;
		this.answerWriteDate = answerWriteDate;
	}
	public Long getAnswerSeq() {
		return answerSeq;
	}
	public void setAnswerSeq(Long answerSeq) {
		this.answerSeq = answerSeq;
	}
	public Long getQnaSeq() {
		return qnaSeq;
	}
	public void setQnaSeq(Long qnaSeq) {
		this.qnaSeq = qnaSeq;
	}
	public String getAnswerContents() {
		return answerContents;
	}
	public void setAnswerContents(String answerContents) {
		this.answerContents = answerContents;
	}
	public String getAnswerWriter() {
		return answerWriter;
	}
	public void setAnswerWriter(String answerWriter) {
		this.answerWriter = answerWriter;
	}
	public Instant getAnswerWriteDate() {
		return answerWriteDate;
	}
	public void setAnswerWriteDate(Instant answerWriteDate) {
		this.answerWriteDate = answerWriteDate;
	}	
	
}
