package com.kdt.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.CurrentPlayListDTO;
import com.kdt.dto.MusicLikeDTO;
import com.kdt.dto.MyMusicLikesDTO;
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
	public ResponseEntity<Long> addFavorite(MusicLikeDTO dto) {
		if (dto.getUserId().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		System.out.println("userid" + dto.getUserId());
		System.out.println("trackid" + dto.getTrackId());
		Long seq = lService.insertFavorite(dto);

		return ResponseEntity.ok(seq);
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<MusicLikeDTO>> getFavorite(@PathVariable String id) {
		List<MusicLikeDTO> list = lService.selectAllById(id);
		return ResponseEntity.ok(list);
	}

	@PostMapping("/delete")
	public ResponseEntity<String> deleteFavorite(MusicLikeDTO dto) {
		lService.deleteFavorite(dto);
		return ResponseEntity.ok(null);
	}

	@PostMapping("/follow")
	public ResponseEntity<String> addFollow(SingerFollowDTO dto) {
		fService.insertFollow(dto);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/isfollow")
	public ResponseEntity<Boolean> getFollowState(SingerFollowDTO dto) {
		System.out.println(dto.getSingerId());
		Boolean state = fService.getFollowState(dto);
		return ResponseEntity.ok(state);
	}

	@PostMapping("/followDelete")
	public ResponseEntity<String> deleteFollow(SingerFollowDTO dto) {
		fService.deleteFollow(dto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/nums/{id}")
	public ResponseEntity<HashMap<String, Object>> getFollow(@PathVariable String id) {
		Long followers = fService.getFollowers(id);
		Long followings = fService.getFollowing(id);

		HashMap<String, Object> data = new HashMap<>();
		data.put("followers", followers);
		data.put("followings", followings);
		return ResponseEntity.ok(data);

	}

	@GetMapping("/all")
	public ResponseEntity<List<MusicLikeDTO>> selectAllTracksById(Principal id) {
		List<MusicLikeDTO> dtos = lService.selectAllTracksById(id);
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/order")
	public ResponseEntity<List<MusicLikeDTO>> selectById(Principal id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "pageSize", defaultValue = "6") int pageSize) {

		List<MusicLikeDTO> dtos = lService.selectById(id, page, pageSize);

		// 검색된 아이템 수가 pageSize보다 작은지 확인
		if (dtos.size() < pageSize) {
			// 이것은 마지막 페이지이므로, 모든 남은 데이터를 반환합니다.
			// 프론트엔드에서는 더 이상 페이지가 없음을 나타내는 플래그를 확인할 수 있습니다.
			return ResponseEntity.ok(dtos);
		}

		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/follwingData/{memberId}")
	public ResponseEntity<List<HashMap<String, Object>>> getFollowingData(@PathVariable String memberId) {
		List<HashMap<String, Object>> list = fService.getMyFollow(memberId);
//		List<FollowingSingerViewDTO> filteredList = new ArrayList<>();
//		for(FollowingSingerViewDTO dto : list) {
//			if(dto.getMemberId().equals(memberId)) {
//				filteredList.add(dto);
//			}
//		}
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/myLikes/{id}")
	public ResponseEntity<List<MyMusicLikesDTO>> getMyLikes(@PathVariable String id){
		List<MyMusicLikesDTO> list = lService.getLikeCount(id);
		return ResponseEntity.ok(list);
	}
	
}
