package com.kdt.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AlbumTag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id ;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="album_id")
	private Album albumId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="tag_id")
	private AlbumTagList albumTagList;

	
	
	public AlbumTag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlbumTag(Long id, Album albumId, AlbumTagList albumTagList) {
		super();
		this.id = id;
		this.albumId = albumId;
		this.albumTagList = albumTagList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Album getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Album albumId) {
		this.albumId = albumId;
	}

	public AlbumTagList getAlbumTagList() {
		return albumTagList;
	}

	public void setAlbumTagList(AlbumTagList albumTagList) {
		this.albumTagList = albumTagList;
	}
	
}
