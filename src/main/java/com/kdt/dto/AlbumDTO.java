package com.kdt.dto;

import java.security.Timestamp;
import java.util.Set;

public class AlbumDTO {

	private Long albumId;
	private String title;
	private Long artistId;
	private Timestamp releaseDate;
	private Long genreId;
	private String coverImagePath;
	private String writer;
	private Set<TrackDTO> tracks;
	
	public AlbumDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AlbumDTO(Long albumId, String title, Long artistId, Timestamp releaseDate, Long genreId,
			String coverImagePath, String writer, Set<TrackDTO> tracks) {
		super();
		this.albumId = albumId;
		this.title = title;
		this.artistId = artistId;
		this.releaseDate = releaseDate;
		this.genreId = genreId;
		this.coverImagePath = coverImagePath;
		this.writer = writer;
		this.tracks = tracks;
	}
	public Long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getArtistId() {
		return artistId;
	}
	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}
	public Timestamp getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Timestamp releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Long getGenreId() {
		return genreId;
	}
	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}
	public String getCoverImagePath() {
		return coverImagePath;
	}
	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Set<TrackDTO> getTracks() {
		return tracks;
	}
	public void setTracks(Set<TrackDTO> tracks) {
		this.tracks = tracks;
	}
	
	
	
	
}
