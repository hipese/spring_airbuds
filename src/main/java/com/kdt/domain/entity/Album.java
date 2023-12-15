package com.kdt.domain.entity;


import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Album {
	
	@Id
	@Column(name="album_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long albumId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="artist_id")
	private String artistId;
	
	@Column(name="release_date")
	private Timestamp releaseDate;
	
	@Column(name="cover_image_path")
	private String coverImagePath;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="album_id")
	private Set<Track> tracks;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="album_id")
	private Set<AlbumWriter> albumWriter;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="album_id")
	private Set<AlbumTag> albumTag;
	

	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Album(Long albumId, String title, String artistId, Timestamp releaseDate, String coverImagePath,
			Set<Track> tracks, Set<AlbumWriter> albumWriter, Set<AlbumTag> albumTag) {
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

	public Timestamp getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Timestamp releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCoverImagePath() {
		return coverImagePath;
	}

	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}

	public Set<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

	public Set<AlbumWriter> getAlbumWriter() {
		return albumWriter;
	}

	public void setAlbumWriter(Set<AlbumWriter> albumWriter) {
		this.albumWriter = albumWriter;
	}

	public Set<AlbumTag> getAlbumTag() {
		return albumTag;
	}

	public void setAlbumTag(Set<AlbumTag> albumTag) {
		this.albumTag = albumTag;
	}
	

	
	
}
