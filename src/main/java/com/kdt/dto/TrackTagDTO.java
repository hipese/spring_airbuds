package com.kdt.dto;

import java.util.Set;

import com.kdt.domain.entity.Track;
import com.kdt.domain.entity.TrackImages;

public class TrackTagDTO {

	private Long id;
	private TrackDTO track;
	private MusicTagDTO musicTags;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TrackDTO getTrack() {
		return track;
	}
	public void setTrack(TrackDTO track) {
		this.track = track;
	}
	public MusicTagDTO getMusicTags() {
		return musicTags;
	}
	public void setMusicTags(MusicTagDTO musicTags) {
		this.musicTags = musicTags;
	}
	public TrackTagDTO(Long id, TrackDTO track, MusicTagDTO musicTags) {
		this.id = id;
		this.track = track;
		this.musicTags = musicTags;
	}
	public TrackTagDTO() {
	}
	
	
}
