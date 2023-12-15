package com.kdt.dto;

public class AlbumTagListDTO {
	private Long tagId;
	private String tagName;
	private String base;
	
	public AlbumTagListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AlbumTagListDTO(Long tagId, String tagName, String base) {
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
