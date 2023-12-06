package com.kdt.services;

import java.io.File;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.domain.entity.Track;
import com.kdt.dto.TrackDTO;
import com.kdt.mappers.TrackMapper;
import com.kdt.repositories.TrackRepository;

@Service
public class TrackService {
	
	@Autowired
	TrackRepository tReop;
	
	@Autowired
	TrackMapper tMapper;
	
	public void insert( MultipartFile[] files,String[] durations) throws Exception {

		// 음원 파일 저장
		 	File uploadPath=new File("c:/tracks");
			
			if(!uploadPath.exists()) {
				uploadPath.mkdir();
			}
				
			for (int i = 0; i < files.length; i++) {
	            MultipartFile file = files[i];
	            String filename = file.getOriginalFilename();
	            String sys_filename=UUID.randomUUID()+filename;
	            File destFile = new File(uploadPath + File.separator + sys_filename);
	            file.transferTo(destFile);
	            
                System.out.println("파일 저장 완료: " + destFile.getAbsolutePath());
                System.out.println("파일 길이(초): " + durations[i]); // duration 출력
                
                
             // 문자열을 double로 변환하고 long으로 반올림
                double durationDouble = Double.parseDouble(durations[i]);
                long durationInSeconds = Math.round(durationDouble);
                String timeString = convertSecondsToTimeString(durationInSeconds);
                Time durationTime = Time.valueOf(timeString);
                
                
                TrackDTO dto = new TrackDTO();
                dto.setTitle(filename);
                dto.setAlbumId(null);
                dto.setDuration(durationTime);
                dto.setFilePath(sys_filename);
                dto.setTrackNumber(0L);
                
                System.out.println("저장하기 전");
                tReop.save(tMapper.toEntity(dto));
	        }
			
	}
	
	public List<TrackDTO> selectAll(){
		List<Track> entity=tReop.findAll();
		List<TrackDTO> dtos=tMapper.toDtoList(entity);
		return dtos;
	}
	
	// 초를 HH:mm:ss 형식의 문자열로 변환 (time 형태로 변환)
	private String convertSecondsToTimeString(long seconds) {
	    long hours = seconds / 3600;
	    long minutes = (seconds % 3600) / 60;
	    long secs = seconds % 60;
	    return String.format("%02d:%02d:%02d", hours, minutes, secs);
	}
}
