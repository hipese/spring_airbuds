package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.CurrentPlayListDTO;
import com.kdt.services.CurrentPlayListService;

@RestController
@RequestMapping("/api/cplist")
public class CurrentPlayListController {

	@Autowired
	CurrentPlayListService cpService;

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody CurrentPlayListDTO dto) throws Exception {
		System.out.println(dto.getTrackId());
		cpService.insert(dto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<CurrentPlayListDTO>> selectById(@PathVariable String id,
	        @RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "pageSize", defaultValue = "6") int pageSize) {

	    List<CurrentPlayListDTO> dtos = cpService.selectById(id, page, pageSize);

	    // 검색된 아이템 수가 pageSize보다 작은지 확인
	    if (dtos.size() < pageSize) {
	        // 이것은 마지막 페이지이므로, 모든 남은 데이터를 반환합니다.
	        // 프론트엔드에서는 더 이상 페이지가 없음을 나타내는 플래그를 확인할 수 있습니다.
	        return ResponseEntity.ok(dtos);
	    }

	    return ResponseEntity.ok(dtos);
	}

}
