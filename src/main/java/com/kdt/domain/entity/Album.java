package com.kdt.domain.entity;

import java.security.Timestamp;
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
	private Long artistId;
	
	@Column(name="release_date")
	private Timestamp releaseDate;
	
	@Column(name="genre_id")
	private Long genreId;
	
	@Column(name="cover_image_path")
	private String coverImagePath;
	
	@Column(name="writer")
	private String writer;
	
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

	public Album(Long albumId, String title, Long artistId, Timestamp releaseDate, Long genreId, String coverImagePath,
			String writer, Set<Track> tracks, Set<AlbumWriter> albumWriter, Set<AlbumTag> albumTag) {
		super();
		this.albumId = albumId;
		this.title = title;
		this.artistId = artistId;
		this.releaseDate = releaseDate;
		this.genreId = genreId;
		this.coverImagePath = coverImagePath;
		this.writer = writer;
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
