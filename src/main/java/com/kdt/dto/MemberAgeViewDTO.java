package com.kdt.dto;

public class MemberAgeViewDTO {

	private String ageGroup;
	private Long count;
	public MemberAgeViewDTO() {
		super();
	}
	public MemberAgeViewDTO(String ageGroup, Long count) {
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
