package com.kdt.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.PlaylistDTO;
import com.kdt.services.PlaylistService;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

	@Autowired
	private PlaylistService plServ;
	
	@PostMapping
	public ResponseEntity<Void> insert(Principal principal, @RequestBody PlaylistDTO pldto) {
		String userId = principal.getName();
		pldto.setPlaylistWriteId(userId);
		plServ.insert(pldto);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/track/{playlistSeq}")
	public ResponseEntity<Void> insertPlaylist(@PathVariable Long playlistSeq, @RequestBody PlaylistDTO pldto) {
		pldto.getPlaylistTracks().get(0).setPlaylistParentSeq(playlistSeq);
	    plServ.insertPlaylist(pldto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{playlist_write_id}")
	public ResponseEntity<List<PlaylistDTO>> selectAll(@PathVariable("playlist_write_id") String playlistWriteId) {
	    List<PlaylistDTO> list = plServ.selectAll(playlistWriteId);
	    return ResponseEntity.ok(list);
	}
	
	@PutMapping("/update/{playlistSeq}")
	public ResponseEntity<Void> updatePlaylist(@PathVariable Long playlistSeq, @RequestBody PlaylistDTO pldto) {
		System.out.println(playlistSeq);
		String title = pldto.getPlaylistPlTitle();
		System.out.println(pldto.getPlaylistPlTitle());
	    plServ.updatePlaylist(playlistSeq, title);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{playlist_seq}")
	public ResponseEntity<Void> deletePlaylist(@PathVariable("playlist_seq") Long playlistSeq) {
		plServ.deletePlaylist(playlistSeq);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/track/{playlist_seq}")
	public ResponseEntity<Void> deleteTrack(@PathVariable("playlist_seq") Long playlistSeq) {
		plServ.deleteTrack(playlistSeq);
		return ResponseEntity.ok().build();
	}
	
}
