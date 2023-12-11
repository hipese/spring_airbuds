package com.kdt.dto;

public class QnaFileDTO {

	private Long qnaFileSeq;
	private Long parentSeq;
	private String oriName;
	private String sysName;
	public QnaFileDTO() {
		super();
	}
	public QnaFileDTO(Long qnaFileSeq, Long parentSeq, String oriName, String sysName) {
		super();
		this.qnaFileSeq = qnaFileSeq;
		this.parentSeq = parentSeq;
		this.oriName = oriName;
		this.sysName = sysName;
	}
	public Long getQnaFileSeq() {
		return qnaFileSeq;
	}
	public void setQnaFileSeq(Long qnaFileSeq) {
		this.qnaFileSeq = qnaFileSeq;
	}
	public Long getParentSeq() {
		return parentSeq;
	}
	public void setParentSeq(Long parentSeq) {
		this.parentSeq = parentSeq;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	
	
}
