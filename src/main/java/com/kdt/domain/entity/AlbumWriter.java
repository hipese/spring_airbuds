package com.kdt.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AlbumWriter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;
	
	@Column(name="album_id")
	private Long albumId;
	
	@Column(name="artist_nickname")
	private String artistNickname;
	

	public AlbumWriter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlbumWriter(Long seq, Long albumId, String artistNickname) {
		super();
		this.seq = seq;
		this.albumId = albumId;
		this.artistNickname = artistNickname;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public String getArtistNickname() {
		return artistNickname;
	}

	public void setArtistNickname(String artistNickname) {
		this.artistNickname = artistNickname;
	}
	
	
	
	
}
