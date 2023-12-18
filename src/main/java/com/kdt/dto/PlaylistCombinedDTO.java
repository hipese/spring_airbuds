package com.kdt.dto;

public class PlaylistCombinedDTO {
	private PlaylistDTO playlist;
    private PlaylistTrackDTO playlistTrack;
	public PlaylistDTO getPlaylist() {
		return playlist;
	}
	public void setPlaylist(PlaylistDTO playlist) {
		this.playlist = playlist;
	}
	public PlaylistTrackDTO getPlaylistTrack() {
		return playlistTrack;
	}
	public void setPlaylistTrack(PlaylistTrackDTO playlistTrack) {
		this.playlistTrack = playlistTrack;
	}
	public PlaylistCombinedDTO(PlaylistDTO playlist, PlaylistTrackDTO playlistTrack) {
		this.playlist = playlist;
		this.playlistTrack = playlistTrack;
	}
	public PlaylistCombinedDTO() {
	}
    
    
}
