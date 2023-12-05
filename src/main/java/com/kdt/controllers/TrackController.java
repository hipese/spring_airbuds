package com.kdt.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/track")
public class TrackController {
	
	
	@PostMapping
    public ResponseEntity<Void> uploadMusic(@RequestParam("file") MultipartFile[] files, 
    										@RequestParam("duration") String[] durations) {
		
//		음원 파일을 저장하는 부분
		 File uploadPath=new File("c:/tracks");
			
			if(!uploadPath.exists()) {
				uploadPath.mkdir();
			}
			
		//나중에 중복된 파일 있을수도 있으니 그거 설정 알아서 ㄱ
			for (int i = 0; i < files.length; i++) {
	            MultipartFile file = files[i];
	            String filename = file.getOriginalFilename();
	            File destFile = new File(uploadPath + File.separator + filename);

	            try {
	                file.transferTo(destFile);
	                System.out.println("파일 저장 완료: " + destFile.getAbsolutePath());
	                System.out.println("파일 길이(초): " + durations[i]); // duration 출력
	            } catch (IOException e) {
	                System.out.println("파일 저장 중 오류 발생: " + e.getMessage());
	                return ResponseEntity.internalServerError().build();
	            }
	        }

        return ResponseEntity.ok().build();
    }

}
