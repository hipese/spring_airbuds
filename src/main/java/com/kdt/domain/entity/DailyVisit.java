package com.kdt.domain.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class DailyVisit {

	@Id
	@Column(name = "visit_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long visitSeq;
	
	//@Temporal(TemporalType.DATE)
	@Column(name = "visit_date")
	private Timestamp visitDate;
	
	@Column(name = "visit_count")
	private Long visitCount;

	public DailyVisit() {
		super();
	}

	public DailyVisit(Long visitSeq, Timestamp visitDate, Long visitCount) {
		super();
		this.visitSeq = visitSeq;
		this.visitDate = visitDate;
		this.visitCount = visitCount;
	}

	public Long getVisitSeq() {
		return visitSeq;
	}

	public void setVisitSeq(Long visitSeq) {
		this.visitSeq = visitSeq;
	}

	public Timestamp getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Timestamp visitDate) {
		this.visitDate = visitDate;
	}

	public Long getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}
}
