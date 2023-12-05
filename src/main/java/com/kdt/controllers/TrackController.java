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
    public ResponseEntity<Void> uploadMusic(@RequestParam("file") MultipartFile[] files) {
		
		 File uploadPath=new File("c:/tracks");
			
			if(!uploadPath.exists()) {
				uploadPath.mkdir();
			}
			
		 
		 for (MultipartFile file : files) {
	            String filename = file.getOriginalFilename();
	            File destFile = new File(uploadPath + File.separator + filename);

	            try {
	                file.transferTo(destFile);
	                System.out.println("파일 저장 완료: " + destFile.getAbsolutePath());
	            } catch (IOException e) {
	                System.out.println("파일 저장 중 오류 발생: " + e.getMessage());
	                return ResponseEntity.internalServerError().build();
	            }
	        }

		
        // files 배열을 반복하여 각 파일의 정보를 출력
        for (MultipartFile file : files) {
            System.out.println("파일 이름: " + file.getOriginalFilename());
            System.out.println("파일 크기: " + file.getSize());
            System.out.println("파일 경로: " + file.getResource());
            // 여기에 추가적인 파일 처리 로직을 구현할 수 있습니다.
        }

        return ResponseEntity.ok().build();
    }

}
