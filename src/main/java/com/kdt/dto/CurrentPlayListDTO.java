package com.kdt.dto;

import java.util.Set;

public class CurrentPlayListDTO {

	private Long seq;
	private Long trackId;
	private String id;
	private Set<TrackDTO> tracks;
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public Long getTrackId() {
		return trackId;
	}
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<TrackDTO> getTracks() {
		return tracks;
	}
	public void setTracks(Set<TrackDTO> tracks) {
		this.tracks = tracks;
	}
	public CurrentPlayListDTO(Long seq, Long trackId, String id, Set<TrackDTO> tracks) {
		super();
		this.seq = seq;
		this.trackId = trackId;
		this.id = id;
		this.tracks = tracks;
	}
	public CurrentPlayListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
