package com.kdt.domain.entity;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Qna {

	@Id
	@Column(name = "qna_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qnaSeq;
	
	@Column(name = "qna_title")
	private String qnaTitle;
	
	@Column(name = "qna_writer")
	private String qnaWriter;
	
	@Column(name = "qna_contents")
	private String qnaContents;
	
	@Column(name = "qna_category")
	private String qnaCategory;
	
	@Column(name = "qna_write_date")
	private Timestamp qnaWriteDate;
	
	@Column(name = "qna_answer_state")
	private Long qnaAnswerState;
	
	@Column(name = "qna_public")
	private Long qnaPublic;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_seq")
	private Set<QnaFile> files;

	public Qna() {
		super();
	}

	public Qna(Long qnaSeq, String qnaTitle, String qnaWriter, String qnaContents, String qnaCategory,
			Timestamp qnaWriteDate, Long qnaAnswerState, Long qnaPublic, Set<QnaFile> files) {
		super();
		this.qnaSeq = qnaSeq;
		this.qnaTitle = qnaTitle;
		this.qnaWriter = qnaWriter;
		this.qnaContents = qnaContents;
		this.qnaCategory = qnaCategory;
		this.qnaWriteDate = qnaWriteDate;
		this.qnaAnswerState = qnaAnswerState;
		this.qnaPublic = qnaPublic;
		this.files = files;
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

	public String getQnaWriter() {
		return qnaWriter;
	}

	public void setQnaWriter(String qnaWriter) {
		this.qnaWriter = qnaWriter;
	}

	public String getQnaContents() {
		return qnaContents;
	}

	public void setQnaContents(String qnaContents) {
		this.qnaContents = qnaContents;
	}

	public String getQnaCategory() {
		return qnaCategory;
	}

	public void setQnaCategory(String qnaCategory) {
		this.qnaCategory = qnaCategory;
	}

	public Timestamp getQnaWriteDate() {
		return qnaWriteDate;
	}

	public void setQnaWriteDate(Timestamp qnaWriteDate) {
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

	public Set<QnaFile> getFiles() {
		return files;
	}

	public void setFiles(Set<QnaFile> files) {
		this.files = files;
	}
	
	
}
