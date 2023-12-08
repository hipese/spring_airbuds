package com.kdt.dto;

import java.time.Instant;

public class QnaDTO {

	private Long qnaSeq;
	private String qnaTitle;
	private String qnaContents;
	private String qnaWriter;
	private String qnaFile;
	private Instant qnaWriteDate;
	private Long qnaAnswerState;
	private Long qnaPublic;
	private String qnaCategory;
	public QnaDTO() {
		super();
	}
	public QnaDTO(Long qnaSeq, String qnaTitle, String qnaContents, String qnaWriter, String qnaFile,
			Instant qnaWriteDate, Long qnaAnswerState, Long qnaPublic, String qnaCategory) {
		super();
		this.qnaSeq = qnaSeq;
		this.qnaTitle = qnaTitle;
		this.qnaContents = qnaContents;
		this.qnaWriter = qnaWriter;
		this.qnaFile = qnaFile;
		this.qnaWriteDate = qnaWriteDate;
		this.qnaAnswerState = qnaAnswerState;
		this.qnaPublic = qnaPublic;
		this.qnaCategory = qnaCategory;
	}
	public Long getQnaSeq() {
		return qnaSeq;
	}
	public void setQnaSeq(Long qnaSeq) {
		this.qnaSeq = qnaSeq;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaContents() {
		return qnaContents;
	}
	public void setQnaContents(String qnaContents) {
		this.qnaContents = qnaContents;
	}
	public String getQnaWriter() {
		return qnaWriter;
	}
	public void setQnaWriter(String qnaWriter) {
		this.qnaWriter = qnaWriter;
	}
	public String getQnaFile() {
		return qnaFile;
	}
	public void setQnaFile(String qnaFile) {
		this.qnaFile = qnaFile;
	}
	public Instant getQnaWriteDate() {
		return qnaWriteDate;
	}
	public void setQnaWriteDate(Instant qnaWriteDate) {
		this.qnaWriteDate = qnaWriteDate;
	}
	public Long getQnaAnswerState() {
		return qnaAnswerState;
	}
	public void setQnaAnswerState(Long qnaAnswerState) {
		this.qnaAnswerState = qnaAnswerState;
	}
	public Long getQnaPublic() {
		return qnaPublic;
	}
	public void setQnaPublic(Long qnaPublic) {
		this.qnaPublic = qnaPublic;
	}
	public String getQnaCategory() {
		return qnaCategory;
	}
	public void setQnaCategory(String qnaCategory) {
		this.qnaCategory = qnaCategory;
	}
		
}
