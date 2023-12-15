package com.kdt.domain.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class AlbumTagList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private Long tagId;

	@Column(name = "tag_name")
	private String tagName;

	@Column(name = "base")
	private String base;

	@OneToMany(mappedBy = "albumTagList")
	private Set<AlbumTag> albumTag = new HashSet<>();

	public AlbumTagList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlbumTagList(Long tagId, String tagName, String base, Set<AlbumTag> albumTag) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
		this.base = base;
		this.albumTag = albumTag;
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

	public Set<AlbumTag> getAlbumTag() {
		return albumTag;
	}

	public void setAlbumTag(Set<AlbumTag> albumTag) {
		this.albumTag = albumTag;
	}

	
	
}
