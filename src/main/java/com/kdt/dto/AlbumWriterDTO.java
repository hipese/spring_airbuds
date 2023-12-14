package com.kdt.dto;

public class AlbumWriterDTO {
	private Long seq;
	private Long albumId;
	private String artistId;
	private String artistNickname;
	
	
	public AlbumWriterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AlbumWriterDTO(Long seq, Long albumId, String artistId, String artistNickname) {
		super();
		this.seq = seq;
		this.albumId = albumId;
		this.artistId = artistId;
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
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public String getArtistNickname() {
		return artistNickname;
	}
	public void setArtistNickname(String artistNickname) {
		this.artistNickname = artistNickname;
	}
	
}
