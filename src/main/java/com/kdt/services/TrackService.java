package com.kdt.services;

import java.io.File;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.domain.entity.MusicTags;
import com.kdt.domain.entity.Track;
import com.kdt.domain.entity.TrackTag;
import com.kdt.dto.TrackDTO;
import com.kdt.dto.TrackImageDTO;
import com.kdt.dto.TrackTagDTO;
import com.kdt.mappers.MusicTagMapper;
import com.kdt.mappers.TrackImageMapper;
import com.kdt.mappers.TrackMapper;
import com.kdt.mappers.TrackTagMapper;
import com.kdt.repositories.MusicTagRepository;
import com.kdt.repositories.TrackImageRepository;
import com.kdt.repositories.TrackRepository;
import com.kdt.repositories.TrackTagRepository;

import jakarta.transaction.Transactional;

@Service
public class TrackService {

	@Autowired
	private TrackRepository tRepo;
	@Autowired
	private TrackTagRepository tagRepo;
	@Autowired
	private TrackImageRepository imageRepo;
	@Autowired 
	private MusicTagRepository musicReop;

	@Autowired
	private TrackMapper tMapper;

	@Autowired
	private TrackImageMapper imageMapper;


	@Transactional
	public void insert(MultipartFile files, 
			 		   String name,
					   String durations, 
					   String image_path,
					   MultipartFile imagefile, 
					   String writer, 
					   String[] tag)
			throws Exception {
		
		Track savedTrack = null;
		
		// 음원 파일 저장
		File uploadPath = new File("c:/tracks");
		File imagePath = new File("c:/tracks/image");

		if (!uploadPath.exists()) {
			uploadPath.mkdir();
		}

		if (!imagePath.exists()) {
			imagePath.mkdir();
		}

		if (files!=null) {
			MultipartFile file = files;
			
			String filename = file.getOriginalFilename();
			String sys_filename = UUID.randomUUID() + filename;
			
			// 문자열을 double로 변환하고 long으로 반올림
			double durationDouble = Double.parseDouble(durations);
			long durationInSeconds = Math.round(durationDouble);
			String timeString = convertSecondsToTimeString(durationInSeconds);
			Time durationTime = Time.valueOf(timeString);

			TrackDTO dto = new TrackDTO();
			dto.setTitle(name);
			dto.setAlbumId(null);
			dto.setDuration(durationTime);
			dto.setFilePath(sys_filename);
			dto.setTrackNumber(0L);
			dto.setViewCount(0L);
			dto.setWriter(writer);
			
			Track entity= tMapper.toEntity(dto);
			Set<TrackTag> trackTags = new HashSet<>();
			
			for(int j = 0; j < tag.length; j++) {
				System.out.println("MuiscTag에서 id 값: "+tag[j]);

//				muisctag에 존재하는 tag와 알맞은 값
				MusicTags musictag=musicReop.findById(Long.parseLong(tag[j])).get();
				
				TrackTag tracktag=new TrackTag();
				tracktag.setMusicTags(musictag);
				tracktag.setTrack(entity);
				
//				값을 샛팅한 후 저장
				tagRepo.save(tracktag);
				trackTags.add(tracktag);
			}
			
			entity.setTrackTags(trackTags);
			savedTrack= tRepo.save(entity);
			
			File destFile = new File(uploadPath + File.separator + sys_filename);
			file.transferTo(destFile);
		}
		
		 // 이미지 파일 처리
	    if (imagefile != null) {
	        MultipartFile imgFile = imagefile;
	        String imageName = imgFile.getOriginalFilename();
	        String sys_imageName = UUID.randomUUID().toString() + "_" + imageName;
	        File destImageFile = new File(imagePath, sys_imageName);
	        imgFile.transferTo(destImageFile);

	        // 이미지 파일 경로 업데이트
	        image_path = sys_imageName;
	        
	        TrackImageDTO imagedto=new TrackImageDTO();
	        imagedto.setTrackId(savedTrack.getTrackId());
	        imagedto.setImagePath(sys_imageName);
	        imageRepo.save(imageMapper.toEntity(imagedto));
	    }
		
	}
	
	public void multiUpload(MultipartFile[] files, 
	 		   String[] name,
			   String[] durations, 
			   String[] image_path,
			   MultipartFile imagefile, 
			   String[] writer, 
			   String[] tag)
	throws Exception{
		
	}

	public List<TrackDTO> selectAll() {
		List<Track> entity = tRepo.findAllByFetchJoin();
		List<TrackDTO> dtos = tMapper.toDtoList(entity);
		return dtos;
	}
	
	public List<TrackDTO> selectOne(List<Long> list) {
		List<Track> entity = tRepo.findAllById(list);
		List<TrackDTO>dtos = tMapper.toDtoList(entity);
		return dtos;
	}
	
	public List<TrackDTO> selectByWriter(String writer){
		List<Track> entity = tRepo.findAllByWriterStartingWith(writer);
		List<TrackDTO> dtos=tMapper.toDtoList(entity);
		return dtos;
	}
	
	public List<TrackDTO> recentAll() {
		Pageable pageable = PageRequest.of(0, 10);
		List<Track> entity = tRepo.findAllByOrderByTrackIdDesc(pageable);
		List<TrackDTO> dtos = tMapper.toDtoList(entity);
		return dtos;
	}

	public void deleteByIdTrack(String track_id) {

		Long realId = Long.parseLong(track_id);

//		실제 경로에 존재하는 음원 삭제
		Track entity = tRepo.findById(realId).orElse(null);
		
		
		if (entity != null) {
			String filePath = "c:/tracks" + File.separator + entity.getFilePath();
			File fileToDelete = new File(filePath);
			
//			이미지도 삭제하는 기능 넣어야 함 만약

			if (fileToDelete.exists()) {
				boolean isDeleted = fileToDelete.delete();
				if (isDeleted) {
					System.out.println("파일 삭제 완료: " + filePath);
					tRepo.deleteById(realId); // 데이터베이스에서 삭제
				} else {
					System.out.println("파일 삭제 실패: " + filePath);
				}
			} else {
				System.out.println("파일이 존재하지 않습니다: " + filePath);
			}
		} else {
			System.out.println("해당 ID의 트랙을 찾을 수 없습니다: " + realId);
		}
	}

	// 초를 HH:mm:ss 형식의 문자열로 변환 (time 형태로 변환)
	private String convertSecondsToTimeString(long seconds) {
		long hours = seconds / 3600;
		long minutes = (seconds % 3600) / 60;
		long secs = seconds % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, secs);
	}
}
