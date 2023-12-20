package com.kdt.dto;

import java.time.Instant;

public class DailyVisitDTO {

	private Long visitSeq;
	private Instant visitDate;
	private Long visitCount;
	public DailyVisitDTO() {
		super();
	}
	public DailyVisitDTO(Long visitSeq, Instant visitDate, Long visitCount) {
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
	public Instant getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Instant visitDate) {
		this.visitDate = visitDate;
	}
	public Long getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}

}
