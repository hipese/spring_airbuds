package com.kdt.controllers;

import java.util.HashMap;
import java.security.Principal;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.MusicLikeDTO;
import com.kdt.dto.SingerFollowDTO;
import com.kdt.services.FollowService;
import com.kdt.services.LikeService;

@RestController
@RequestMapping("/api/like")
public class LikenFollowController {

	@Autowired
	private LikeService lService;
	
	@Autowired
	private FollowService fService;
	
	@PostMapping
	public ResponseEntity<Long> addFavorite(MusicLikeDTO dto){
		if(dto.getUserId().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		System.out.println("userid"+dto.getUserId());
		System.out.println("trackid"+dto.getTrackId());
		Long seq = lService.insertFavorite(dto);
		
		return ResponseEntity.ok(seq);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<MusicLikeDTO>> getFavorite(@PathVariable String id){
		List<MusicLikeDTO> list = lService.selectAllById(id);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteFavorite(MusicLikeDTO dto){
		System.out.println(dto.getUserId());
		lService.deleteFavorite(dto);
		return ResponseEntity.ok(null);
	}
	
	@PostMapping("/follow")
	public ResponseEntity<String> addFollow(SingerFollowDTO dto){		
		fService.insertFollow(dto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/isfollow")
	public ResponseEntity<Boolean> getFollowState(SingerFollowDTO dto){
		System.out.println(dto.getSingerId());
		Boolean state = fService.getFollowState(dto);
		return ResponseEntity.ok(state);
	}
	
	@PostMapping("/followDelete")
	public ResponseEntity<String> deleteFollow(SingerFollowDTO dto){
		fService.deleteFollow(dto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/nums/{id}")
	public ResponseEntity<HashMap<String, Object>> getFollow(@PathVariable String id){
		Long followers = fService.getFollowers(id);
		Long followings = fService.getFollowing(id);
		
		HashMap<String, Object> data = new HashMap<>();
		data.put("followers", followers);
		data.put("followings", followings);
		return ResponseEntity.ok(data);
		
	}
	
	@GetMapping("/follwingData/{memberId}")
	public ResponseEntity<List<HashMap<String, Object>>> getFollowingData(@PathVariable String memberId){
		List<HashMap<String, Object>> list = fService.getMyFollow(memberId);
		return ResponseEntity.ok(list);
	}
}
