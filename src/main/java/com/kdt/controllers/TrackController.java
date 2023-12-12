package com.kdt.controllers;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    										@RequestParam("image_path") String image_path,
    										@RequestParam("releaseDate") String releaseDate,
    										@RequestParam(value = "imagefile", required = false) MultipartFile imagefile,
    										@RequestParam("writer") String writer,
    										@RequestParam(value="tag", required = false) Long[] tag,
    										@RequestParam("login") String loginId) throws Exception {
		


		
		tService.insert(files,name,durations,image_path,imagefile,writer,tag,releaseDate,loginId);
        return ResponseEntity.ok().build();
    }
	
	
	@PostMapping("/multiUpload")
    public ResponseEntity<Void> multiUpload(@RequestParam("file") MultipartFile[] files, 
    										@RequestParam("name") String[] name, 
    										@RequestParam("duration") String[] durations,
    										@RequestParam("image_path") String[] image_path,
    										@RequestParam("releaseDate") String releaseDate,
    										@RequestParam(value = "imagefile", required = false) MultipartFile imagefile,
    										@RequestParam("writer") String[] writer,
    										@RequestParam(value="tag", required = false) Long[] tag) throws Exception {
		

//		System.out.println("name: "+name[0]+"이거 다음행 날짜값 나와야함");
//		System.out.println(releaseDate);
//		System.out.println(tag);


//		tService.insertAlbum(files,name,durations,image_path,releaseDate,imagefile,writer,playlist);
        return ResponseEntity.ok().build();
    }
	
	@GetMapping
	public ResponseEntity<List<TrackDTO>> selectAll(){
		List<TrackDTO> dtos=tService.selectAll();
		return ResponseEntity.ok(dtos);
	}
	

	@GetMapping("/bywriter/{writer}")
	public ResponseEntity<List<TrackDTO>> selectByWriter(@PathVariable String writer){
		List<TrackDTO> dtos=tService.selectByWriter(writer);
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/findById/{write_id}")
	public ResponseEntity<List<TrackDTO>> selectfindById(@PathVariable String write_id){
		List<TrackDTO> dtos=tService.selectfindById(write_id);
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/recent")
	public ResponseEntity<List<TrackDTO>> recentAll() {
		List<TrackDTO> dtos=tService.recentAll();
		return ResponseEntity.ok(dtos);
	}
	
	@DeleteMapping("/{track_id}")
	public ResponseEntity<Void> deleteByIdTrack(@PathVariable Long track_id){
		
		tService.deleteByIdTrack(track_id);
		return ResponseEntity.ok().build();
	}
	
	

}
