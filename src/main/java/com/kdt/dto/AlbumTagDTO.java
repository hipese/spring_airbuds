package com.kdt.dto;

public class AlbumTagDTO {
	private Long id;
	private AlbumDTO album;
	private AlbumTagListDTO albumTagList;
	
	public AlbumTagDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AlbumTagDTO(Long id, AlbumDTO album, AlbumTagListDTO albumTagList) {
		super();
		this.id = id;
		this.album = album;
		this.albumTagList = albumTagList;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AlbumDTO getAlbum() {
		return album;
	}
	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}
	public AlbumTagListDTO getAlbumTagList() {
		return albumTagList;
	}
	public void setAlbumTagList(AlbumTagListDTO albumTagList) {
		this.albumTagList = albumTagList;
	}
	
	
	
}
