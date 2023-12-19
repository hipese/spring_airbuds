package com.kdt.domain.entity;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Immutable
public class MemberAgeView {

	@Id
	@Column(name = "age_group")
	private String ageGroup;
	
	@Column(name = "count")
	private Long count;

	public MemberAgeView() {
		super();
	}

	public MemberAgeView(String ageGroup, Long count) {
		super();
		this.ageGroup = ageGroup;
		this.count = count;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}	
	
}
