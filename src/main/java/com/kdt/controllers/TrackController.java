package com.kdt.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.dto.LikeTrackViewDTO;
import com.kdt.dto.TrackDTO;
import com.kdt.services.TrackService;

@RestController
@RequestMapping("/api/track")
public class TrackController {

	@Autowired
	TrackService tService;
	
	private static final Logger logger=LoggerFactory.getLogger(TrackController.class);
	
	@PostMapping
	public ResponseEntity<Void> uploadMusic(@RequestParam("file") MultipartFile files,
											@RequestParam("name") String name, 
											@RequestParam("duration") String durations,
											@RequestParam("image_path") String image_path, @RequestParam("releaseDate") String releaseDate,
											@RequestParam(value = "imagefile", required = false) MultipartFile imagefile,
											@RequestParam("writer") String writer, 
											@RequestParam(value = "tag", required = false) Long[] tag,
											@RequestParam("login") String loginId) throws Exception {
		
	
		tService.insert(files, name, durations, image_path, imagefile, writer, tag, releaseDate, loginId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/multiUpload")
	public ResponseEntity<Void> multiUpload(@RequestParam("file") MultipartFile[] files,
											@RequestParam("name") String[] name, 
											@RequestParam("duration") String[] durations,
											@RequestParam("image_path") String[] image_path, 
											@RequestParam("releaseDate") String releaseDate,
											@RequestParam(value = "titleImage", required = false) MultipartFile titleImage,
											@RequestParam(value = "imagefile", required = false) MultipartFile[] imagefile,
											@RequestParam("writer") String[] writer,
											@RequestParam MultiValueMap<String, String> trackTags,
											@RequestParam("login") String loginId)throws Exception {
		
		 for (int i = 0; i < files.length; i++) {
		        // 각 파일에 대한 태그 처리
		        List<Long> tagIds = new ArrayList<>();
		        for (String tagKey : trackTags.keySet()) {
		            if (tagKey.startsWith("tags[" + i + "]")) {
		                tagIds.addAll(trackTags.get(tagKey).stream()
		                                .map(Long::parseLong)
		                                .collect(Collectors.toList()));
		            }
		        }
		       
		        
		        MultipartFile currentImageFile = null;
		        if (imagefile != null && imagefile.length > i) {
		            currentImageFile = imagefile[i];
		        }
		        
		        // 각 파일에 대한 데이터 저장
		        tService.insert(files[i], name[i], durations[i], image_path[0], 
		        		currentImageFile, writer[i], tagIds.toArray(new Long[0]), 
		                        releaseDate, loginId);
		    }
		return ResponseEntity.ok().build();
	}
	
	
	@PostMapping("/update")
	public ResponseEntity<TrackDTO> updateTrack(@RequestParam("trackId") Long trackId, 
											@RequestParam("title") String title, 
											@RequestParam("previmagePath") String previmagePath,
											@RequestParam(value = "imagefile", required = false) MultipartFile imagefile,
											@RequestParam("writer") String writer, 
											@RequestParam(value = "tags", required = false)  Long[] tag)throws Exception{
		
		TrackDTO dto=tService.updateTrack(trackId,title,previmagePath,imagefile,writer,tag);
		
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/albumIdSave")
	public ResponseEntity<Void> albumIdSave(@RequestParam Long trackId, @RequestParam Long albumId) {
	    
		tService.albumIdSave(trackId,albumId);
	    return ResponseEntity.ok().build();
	}
	 
	@PostMapping("/albumIdDelete")
	public ResponseEntity<Void> albumIdDelete(@RequestParam Long trackId) {
	    
		tService.albumIdDelete(trackId);
	    return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<TrackDTO>> selectAll() {
		List<TrackDTO> dtos = tService.selectAll();
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/isEdit/{artistId}")
	public ResponseEntity<Boolean> isEditTrack(Principal principal, @PathVariable String writeId){
		
		
		boolean isEdit=tService.isEditTrack(principal,writeId);
		
		return ResponseEntity.ok(isEdit);
	}
	
	
	@GetMapping("/LoginTracks")
	public ResponseEntity<List<TrackDTO>> LoginTracks(Principal principal) {
		
		List<TrackDTO> dtos = tService.LoginTracks(principal);
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/bywriter/{writer}")
	public ResponseEntity<List<TrackDTO>> selectByWriter(@PathVariable String writer) {
		List<TrackDTO> dtos = tService.selectByWriter(writer);
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/bytrack_id/{track_id}")
	public ResponseEntity<TrackDTO> selectByIdTrack(@PathVariable Long track_id) {
		TrackDTO dto = tService.selectByIdTrack(track_id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/findById/{write_id}")
	public ResponseEntity<List<TrackDTO>> selectfindById(@PathVariable String write_id) {
		List<TrackDTO> dtos = tService.selectfindById(write_id);
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/recent")
	public ResponseEntity<List<TrackDTO>> recentAll() {
		List<TrackDTO> dtos = tService.recentAll();
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/searchText/{searchText}")
	public ResponseEntity<List<TrackDTO>> searchTrackByText(@PathVariable String searchText){
		
		logger.debug("값 온다. "+searchText);
		List<TrackDTO> dtos = tService.searchTrackByText(searchText);
		return ResponseEntity.ok(dtos);
	}
	

	@DeleteMapping("/{track_id}")
	public ResponseEntity<Void> deleteByIdTrack(@PathVariable Long track_id) {
		
		tService.deleteByIdTrack(track_id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/like_count/{write_id}")
	public ResponseEntity<List<LikeTrackViewDTO>> getLikeTrack(@PathVariable String write_id){
		List<LikeTrackViewDTO> list = tService.getTrackLike(write_id);
		return ResponseEntity.ok(list);
	}

}
