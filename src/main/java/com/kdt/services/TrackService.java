package com.kdt.services;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.domain.entity.LikeTrackView;
import com.kdt.domain.entity.MusicTags;
import com.kdt.domain.entity.Track;
import com.kdt.domain.entity.TrackImages;
import com.kdt.domain.entity.TrackTag;
import com.kdt.dto.LikeTrackViewDTO;
import com.kdt.dto.TrackDTO;
import com.kdt.dto.TrackImageDTO;
import com.kdt.mappers.LikeTrackViewMapper;
import com.kdt.mappers.TrackImageMapper;
import com.kdt.mappers.TrackMapper;
import com.kdt.repositories.LikeTrackViewRepository;
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
	
	@Autowired
	private LikeTrackViewMapper ltvMapper;
	
	@Autowired
	private LikeTrackViewRepository ltvRepo;

	@Transactional
	public void insert(MultipartFile files, 
			 		   String name,
					   String durations, 
					   String image_path,
					   MultipartFile imagefile, 
					   String writer, 
					   Long[] tag,
					   String releaseDate,
					   String loginId)
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
			dto.setReleaseDate(Instant.parse(releaseDate));
			dto.setWriteId(loginId);
			dto.setBan(0L);
			
			Track entity= tMapper.toEntity(dto);
			Set<TrackTag> trackTags = new HashSet<>();
			
			for(int j = 0; j < tag.length; j++) {
				
//				muisctag에 존재하는 tag와 알맞은 값
				MusicTags musictag=musicReop.findById(tag[j]).get();
				
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
	    
	    if(imagefile==null) {
	    		image_path="NULL";
		        TrackImageDTO imagedto=new TrackImageDTO();
		        imagedto.setTrackId(savedTrack.getTrackId());
		        imagedto.setImagePath(image_path);
		        imageRepo.save(imageMapper.toEntity(imagedto));
	    }
		
	}
	
	
	@Transactional
	public TrackDTO updateTrack(Long trackId, 
							String title, 
							String previmagePath,
							MultipartFile imagefile,
							String writer, 
							Long[] tag) throws Exception {
		
		Track entity=tRepo.findById(trackId).get();
		entity.setTitle(title);
		entity.setWriter(writer);
		
//		변경된 이미지가 있으면 교체
		if(imagefile!=null) {
			TrackImages ientity=imageRepo.findByTrackImagesTrackId(trackId);
			
			File imagePath = new File("c:/tracks/image");
			if (!imagePath.exists()) {
				imagePath.mkdir();
			}
//			기존에 존재하는 이미지를 삭제
		    if(previmagePath != null && !previmagePath.isEmpty()) {
		        File prevImageFile = new File(imagePath, previmagePath);
		        if(prevImageFile.exists()) {
		            boolean isDeleted = prevImageFile.delete();
		            if(!isDeleted) {
		                throw new IOException("Failed to delete existing image file: " + prevImageFile.getAbsolutePath());
		            }
		        }
		    }
			
//			새로운 이미지를 저장 이미지를 삭제
			 MultipartFile imgFile = imagefile;
		     String imageName = imgFile.getOriginalFilename();
		     String sys_imageName = UUID.randomUUID().toString() + "_" + imageName;
		     File destImageFile = new File(imagePath, sys_imageName);
		     imgFile.transferTo(destImageFile);

			
//			경로 교체
			ientity.setImagePath(sys_imageName);
			
			
			imageRepo.save(ientity);
		}
		
//		기존에 있는 trackTags전부 삭제 
		tagRepo.deleteAllByTrackTagTrackId(trackId);
	
//		그 후 다시 설정
		Set<TrackTag> trackTags = new HashSet<>();
		for(int i=0;i<tag.length;i++) {
			MusicTags mentity=musicReop.findById(tag[i]).get();
			
			TrackTag tracktag=new TrackTag();
			tracktag.setMusicTags(mentity);
			tracktag.setTrack(entity);
			
			tagRepo.save(tracktag);
			trackTags.add(tracktag);
		}
		entity.setTrackTags(trackTags);
		tRepo.save(entity);
		
		return tMapper.toDto(entity);
		
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

	public TrackDTO selectByIdTrack(Long track_id){
		Track entity = tRepo.findById(track_id).get();
		TrackDTO dtos=tMapper.toDto(entity);
		return dtos;
	}
	
	
	public List<TrackDTO> selectfindById(String write_id){
		List<Track> entity = tRepo.findAllByWriterIdStartingWith(write_id);
		List<TrackDTO> dtos=tMapper.toDtoList(entity);
		return dtos;
	}
	
	public List<TrackDTO> searchTrackByText(String searchText){
		
		List<Track> entity=tRepo.findAllByTitleOrWriter(searchText);
		List<TrackDTO> dtos=tMapper.toDtoList(entity);
		return dtos;
	}
	
	public List<TrackDTO> recentAll() {
		Pageable pageable = PageRequest.of(0, 10);
		List<Track> entity = tRepo.findAllByOrderByTrackIdDesc(pageable);
		List<TrackDTO> dtos = tMapper.toDtoList(entity);
		return dtos;
	}
	
	
	public void deleteByIdTrack(Long track_id) {

//		실제 경로에 존재하는 음원 삭제
		Track entity = tRepo.findById(track_id).orElse(null);
	
		TrackImages imageEntity=imageRepo.findByTrackImagesTrackId(track_id);
		
		System.out.println("가져온 음원 이미지 id값 "+imageEntity.getImageId());
		System.out.println("가져온 음원 이미지 경로 "+imageEntity.getImagePath());
		
		if (entity != null) {
			
			//track_id과 일치하는 테그를 전부 삭제한다.
			tagRepo.deleteAllByTrackTagTrackId(track_id);
			
			
//			경로에 이미지가 있으면 삭제하고 데이터베이스에 값을 지운다.
			String imagePath="c:/tracks/image"+ File.separator+imageEntity.getImagePath();
			File imageToDelete = new File(imagePath);
			
			if (imageToDelete.exists()) {
				imageToDelete.delete();
				imageRepo.deleteById(track_id);// 데이터베이스에서 이미지 삭제
				
			} else {
				System.out.println("파일이 존재하지 않습니다: " + imagePath);
			}
			
//			음원에 값을 확인하고 
			String filePath = "c:/tracks" + File.separator + entity.getFilePath();
			File fileToDelete = new File(filePath);
			

			if (fileToDelete.exists()) {
				fileToDelete.delete();
				tRepo.deleteById(track_id); // 데이터베이스에서 삭제
				
			} else {
				System.out.println("파일이 존재하지 않습니다: " + filePath);
			}
		} else {
			System.out.println("해당 ID의 트랙을 찾을 수 없습니다: " + track_id);
		}
	}

	// 초를 HH:mm:ss 형식의 문자열로 변환 (time 형태로 변환)
	private String convertSecondsToTimeString(long seconds) {
		long hours = seconds / 3600;
		long minutes = (seconds % 3600) / 60;
		long secs = seconds % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, secs);
	}
	
	public List<LikeTrackViewDTO> getTrackLike(String write_id){
		List<LikeTrackView> ltv = ltvRepo.selectCountByName(write_id);
		List<LikeTrackViewDTO> dtoList = ltvMapper.toDtoList(ltv);
		return dtoList;
	}
}
