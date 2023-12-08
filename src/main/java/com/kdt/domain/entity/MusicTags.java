package com.kdt.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MusicTags {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tag_id")
	private Long tagId;
	
	@Column(name="tag_name")
	private String tagName;
	
	@Column(name="base")
	private String base;

	
	public MusicTags() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MusicTags(Long tagId, String tagName, String base) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
		this.base = base;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
	
	
	
	
}
