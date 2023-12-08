package com.kdt.domain.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class QnaAnswer {
	
	@Id
	@Column(name = "answer_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long answerSeq;
	
	@Column(name = "qna_seq")
	private Long qnaSeq;
	
	@Column(name = "answer_contents")
	private String answerContents;
	
	@Column(name = "answer_writer")
	private String answerWriter;
	
	@Column(name = "answer_write_date")
	private Timestamp answerWriteDate;

	public QnaAnswer() {
		super();
	}

	public QnaAnswer(Long answerSeq, Long qnaSeq, String answerContents, String answerWriter,
			Timestamp answerWriteDate) {
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

	public Timestamp getAnswerWriteDate() {
		return answerWriteDate;
	}

	public void setAnswerWriteDate(Timestamp answerWriteDate) {
		this.answerWriteDate = answerWriteDate;
	}

	
}
