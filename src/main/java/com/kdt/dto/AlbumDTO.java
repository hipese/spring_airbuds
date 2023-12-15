package com.kdt.dto;


import java.time.Instant;
import java.util.Set;

public class AlbumDTO {
	private Long albumId;
	private String title;
	private String artistId;
	private Instant releaseDate;
	private String coverImagePath;
	private Set<TrackDTO> tracks;
	private Set<AlbumWriterDTO> albumWriter;
	private Set<AlbumTagDTO> albumTag;
	
	public AlbumDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AlbumDTO(Long albumId, String title, String artistId, Instant releaseDate, String coverImagePath,
			Set<TrackDTO> tracks, Set<AlbumWriterDTO> albumWriter, Set<AlbumTagDTO> albumTag) {
		super();
		this.albumId = albumId;
		this.title = title;
		this.artistId = artistId;
		this.releaseDate = releaseDate;
		this.coverImagePath = coverImagePath;
		this.tracks = tracks;
		this.albumWriter = albumWriter;
		this.albumTag = albumTag;
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
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public Instant getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Instant releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getCoverImagePath() {
		return coverImagePath;
	}
	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}
	public Set<TrackDTO> getTracks() {
		return tracks;
	}
	public void setTracks(Set<TrackDTO> tracks) {
		this.tracks = tracks;
	}
	public Set<AlbumWriterDTO> getAlbumWriter() {
		return albumWriter;
	}
	public void setAlbumWriter(Set<AlbumWriterDTO> albumWriter) {
		this.albumWriter = albumWriter;
	}
	public Set<AlbumTagDTO> getAlbumTag() {
		return albumTag;
	}
	public void setAlbumTag(Set<AlbumTagDTO> albumTag) {
		this.albumTag = albumTag;
	}
	
	
	
}
