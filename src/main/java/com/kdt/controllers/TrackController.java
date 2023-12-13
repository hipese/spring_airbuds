package com.kdt.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import com.kdt.dto.TrackDTO;
import com.kdt.services.TrackService;

@RestController
@RequestMapping("/api/track")
public class TrackController {

	@Autowired
	TrackService tService;

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
											@RequestParam(value = "imagefile", required = false) MultipartFile imagefile,
											@RequestParam("writer") String writer,
											@RequestParam MultiValueMap<String, String> trackTags,
											@RequestParam("login") String loginId)throws Exception {

		
		System.out.println(writer);
		
//		 // 모든 파라미터 출력
//	    for (String key : trackTags.keySet()) {
//	        System.out.println(key + ": " + trackTags.get(key));
//	    }
//	    
//	 // 특정 태그 처리 예제
//	    List<Long> tagIdsForFirstFile = new ArrayList<>();
//	    for (String tagKey : trackTags.keySet()) {
//	        if (tagKey.startsWith("tags[0]")) {
//	            tagIdsForFirstFile.add(Long.parseLong(trackTags.getFirst(tagKey)));
//	        }
//	    }
	    
//		 for (int i = 0; i < files.length; i++) {
//		        // 각 파일에 대한 태그 처리
//		        List<Long> tagIds = new ArrayList<>();
//		        for (String tagKey : trackTags.keySet()) {
//		            if (tagKey.startsWith("tags[" + i + "]")) {
//		                tagIds.addAll(trackTags.get(tagKey).stream()
//		                                .map(Long::parseLong)
//		                                .collect(Collectors.toList()));
//		            }
//		        }
//
//		        // 각 파일에 대한 데이터 저장
//		        tService.insert(files[i], name[i], durations[i], image_path[i], 
//		                        imagefile, writer, tagIds.toArray(new Long[0]), 
//		                        releaseDate, loginId);
//		    }
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<TrackDTO>> selectAll() {
		List<TrackDTO> dtos = tService.selectAll();
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

	@DeleteMapping("/{track_id}")
	public ResponseEntity<Void> deleteByIdTrack(@PathVariable Long track_id) {

		tService.deleteByIdTrack(track_id);
		return ResponseEntity.ok().build();
	}

}
