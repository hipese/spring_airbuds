package com.kdt.dto;

import java.util.Set;

import com.kdt.domain.entity.Track;

public class MusicLikeDTO {

	private Long likeSeq;
	private String userId;
	private Long trackId;
	private Set<TrackDTO> tracks;
	public Long getLikeSeq() {
		return likeSeq;
	}
	public void setLikeSeq(Long likeSeq) {
		this.likeSeq = likeSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getTrackId() {
		return trackId;
	}
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}
	public Set<TrackDTO> getTracks() {
		return tracks;
	}
	public void setTracks(Set<TrackDTO> tracks) {
		this.tracks = tracks;
	}
	public MusicLikeDTO(Long likeSeq, String userId, Long trackId, Set<TrackDTO> tracks) {
		super();
		this.likeSeq = likeSeq;
		this.userId = userId;
		this.trackId = trackId;
		this.tracks = tracks;
	}
	public MusicLikeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
