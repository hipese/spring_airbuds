package com.kdt.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class QnaFile {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qnaFileSeq;
	
	@Column(name = "parent_seq")
	private Long parentSeq;
	
	@Column(name = "ori_name")
	private String oriName;
	
	@Column(name = "sys_name")
	private String sysName;

	public QnaFile() {
		super();
	}

	public QnaFile(Long qnaFileSeq, Long parentSeq, String oriName, String sysName) {
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
