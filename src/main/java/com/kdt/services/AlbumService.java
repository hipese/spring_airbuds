package com.kdt.services;

import java.io.File;
import java.security.Principal;
import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.domain.entity.Album;
import com.kdt.domain.entity.AlbumTag;
import com.kdt.domain.entity.AlbumTagList;
import com.kdt.domain.entity.AlbumWriter;
import com.kdt.domain.entity.MusicTags;
import com.kdt.domain.entity.Track;
import com.kdt.domain.entity.TrackTag;
import com.kdt.dto.AlbumDTO;
import com.kdt.dto.TrackDTO;
import com.kdt.dto.TrackImageDTO;
import com.kdt.mappers.AlbumMapper;
import com.kdt.mappers.TrackImageMapper;
import com.kdt.mappers.TrackMapper;
import com.kdt.repositories.AlbumRepository;
import com.kdt.repositories.AlbumTagListRepository;
import com.kdt.repositories.AlbumTagRepository;
import com.kdt.repositories.AlbumWriterRepository;
import com.kdt.repositories.MusicTagRepository;
import com.kdt.repositories.TrackImageRepository;
import com.kdt.repositories.TrackRepository;
import com.kdt.repositories.TrackTagRepository;

import jakarta.transaction.Transactional;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository aRepo;
	@Autowired
	private AlbumWriterRepository awRepo;
	@Autowired
	private AlbumTagListRepository atlRepo;
	@Autowired
	private TrackRepository tRepo;
	@Autowired
	private AlbumTagRepository atRepo;
	@Autowired
	private MusicTagRepository musicReop;
	@Autowired
	private TrackTagRepository tagRepo;
	@Autowired
	private TrackImageRepository imageRepo;

	@Autowired
	private AlbumMapper aMapper;
	@Autowired
	private TrackMapper tMapper;
	@Autowired
	private TrackImageMapper imageMapper;

	@Transactional
	public void insertAlbum(MultipartFile[] files, String[] name, String[] durations, String[] image_path,
			String releaseDate, MultipartFile titleImage, String[] writers, Long[] albumselectTag, Long[] order,
			String albumTitle, String loginId, MultiValueMap<String, String> trackTags) throws Exception {

		File imagePath = new File("c:/tracks/image");
		File uploadPath = new File("c:/tracks");

		String sys_imageName = UUID.randomUUID().toString() + "_" + titleImage.getOriginalFilename();

//		엘범 저장
		AlbumDTO adto = new AlbumDTO();
		adto.setTitle(albumTitle);
		adto.setArtistId(loginId);
		adto.setReleaseDate(Instant.parse(releaseDate));
		adto.setCoverImagePath(sys_imageName);

		Album entity = aRepo.save(aMapper.toEntity(adto));
		Album savaAlum = aRepo.save(entity);

		// 각 파일별 태그 ID 배열 생성
		List<Long[]> tagIdsForFiles = extractTagIdsForFiles(trackTags, files.length);
		
		
//		각각의 track값을 같이 저장하는 기능
		Set<Track> track = new HashSet<>();
		for (int i = 0; i < files.length; i++) {
//			음원 저장
			if (files[i] != null) {
				Track savedTrack = null;

				MultipartFile Muiscfile = files[i];

				String filename = Muiscfile.getOriginalFilename();
				String sys_filename = UUID.randomUUID() + filename;

				// 문자열을 double로 변환하고 long으로 반올림
				double durationDouble = Double.parseDouble(durations[i]);
				long durationInSeconds = Math.round(durationDouble);
				String timeString = convertSecondsToTimeString(durationInSeconds);
				Time durationTime = Time.valueOf(timeString);

				TrackDTO dto = new TrackDTO();
				dto.setTitle(name[i]);
				dto.setAlbumId(entity.getAlbumId());
				dto.setTrackNumber(order[i]);
				dto.setDuration(durationTime);
				dto.setFilePath(sys_filename);
				dto.setViewCount(0L);
				dto.setWriter(writers[i]);
				dto.setReleaseDate(Instant.parse(releaseDate));
				dto.setWriteId(loginId);

				Track trackEntity = tMapper.toEntity(dto);
				Set<TrackTag> trackTag = new HashSet<>();
				
				// 해당 파일의 태그 처리
	            Long[] tagIdsArray = tagIdsForFiles.get(i);
	            for (Long tagId : tagIdsArray) {
	                MusicTags musicTag = musicReop.findById(tagId).orElse(null);
	                if (musicTag != null) {
	                    TrackTag tag = new TrackTag();
	                    tag.setMusicTags(musicTag);
	                    tag.setTrack(trackEntity);
	                    tagRepo.save(tag);
	                    trackTag.add(tag);
	                }
	            }

				trackEntity.setTrackTags(trackTag);
				savedTrack = tRepo.save(trackEntity);

//				set에 track값을 저장
				track.add(trackEntity);
				
				// 트랙 이미지 파일 처리
				if (titleImage != null) {
					
					TrackImageDTO imagedto = new TrackImageDTO();
					imagedto.setTrackId(savedTrack.getTrackId());
					imagedto.setImagePath(sys_imageName);
					imageRepo.save(imageMapper.toEntity(imagedto));
				}

				if (titleImage == null) {
					image_path[i] = "NULL";
					TrackImageDTO imagedto = new TrackImageDTO();
					imagedto.setTrackId(savedTrack.getTrackId());
					imagedto.setImagePath(image_path[i]);
					imageRepo.save(imageMapper.toEntity(imagedto));
				}

				File destFile = new File(uploadPath + File.separator + sys_filename);
				Muiscfile.transferTo(destFile);
			}
		}

//		앨범 이미지 생성
		if (!imagePath.exists()) {
			imagePath.mkdir();
		}

		if (titleImage != null && !titleImage.isEmpty()) {
			File destImageFile = new File(imagePath, sys_imageName);
			titleImage.transferTo(destImageFile);

		}

//		엘범 태그값 저장
		 Set<AlbumTag> albumTags = createAlbumTags(albumselectTag, entity);

		   
		 entity.setAlbumTag(albumTags);

//		엘범 제작자 저장
		Set<AlbumWriter> albumWriters = new HashSet<>();

		for (int i = 0; i < writers.length; i++) {

			AlbumWriter writerNickname = new AlbumWriter();
			writerNickname.setAlbumId(savaAlum.getAlbumId());
			writerNickname.setArtistNickname(writers[i]);

			awRepo.save(writerNickname);
			albumWriters.add(writerNickname);
		}

		entity.setAlbumWriter(albumWriters);

		aRepo.save(entity);
	}
	
	public List<AlbumDTO> selectByLoginId(Principal principal){
		String artist_id=principal.getName();
		
		List<Album> entity=aRepo.findAllByArtistIdStartingWith(artist_id);
		List<AlbumDTO> dtos=aMapper.toDtoList(entity);
		return dtos;
	}
	
	
	@Transactional
	public AlbumDTO updateAlbum(MultipartFile[] files, 
								String[] name,
								String[] durations,
								String[] writers,
								String[] image_path,
								MultipartFile titleImage,
								Long[] albumselectTag,
								String albumTitle,
								String [] albumsWriter,
								String [] Tracktitles,
								String prevImage,
								Long[] deleteTrack,
								MultiValueMap<String, String> trackTags,
								Long albumId)throws Exception {
		
		
		System.out.println("files null이여도 옵니다.: "+ files);
//		앨범 변경
		Album entity= aRepo.findById(albumId).get();
		File imagePath = new File("c:/tracks/image");
		entity.setTitle(albumTitle);
		String sys_imageName = null ;
		System.out.println("현재 이미지: "+entity.getCoverImagePath());
		System.out.println("예전 이미지: "+prevImage);
		
		if(titleImage!=null) {
			sys_imageName=UUID.randomUUID().toString() + "_" + titleImage.getOriginalFilename();
			
//			이미지가 변경되었을 시 동작
			if(!(titleImage.getOriginalFilename().equals(prevImage))) {
				
				entity.setCoverImagePath(sys_imageName);
				
				// Save new image
		        if (!imagePath.exists()) {
		            imagePath.mkdir();
		        }
		        File newImageFile = new File(imagePath, sys_imageName);
		        titleImage.transferTo(newImageFile);

		        // Delete old image file
		        deleteOldImage(prevImage);
			}
			
		}
		
		
//		엘범 테그 설정하는 기능
		Set<AlbumTag> albumTags = createAlbumTags(albumselectTag, entity);
		entity.setAlbumTag(albumTags);
		
		aRepo.save(entity);
		
//		트랙을 삽입하는 로직 작성
		if(files!=null) {
//			삭제할 트랙이 있으면 먼저 제거
			if(deleteTrack!=null) {
				for(int i=0;i<deleteTrack.length;i++) {
					tRepo.deleteById(deleteTrack[i]);
				}
			}
			// 각 파일별 태그 ID 배열 생성
			List<Long[]> tagIdsForFiles = extractTagIdsForFiles(trackTags, files.length);
			
			File uploadPath = new File("c:/tracks");
			Set<Track> track = new HashSet<>();
			for (int i = 0; i < files.length; i++) {
//				음원 저장
				if (files[i] != null) {
					Track savedTrack = null;

					MultipartFile Muiscfile = files[i];

					String filename = Muiscfile.getOriginalFilename();
					String sys_filename = UUID.randomUUID() + filename;

					// 문자열을 double로 변환하고 long으로 반올림
					double durationDouble = Double.parseDouble(durations[i]);
					long durationInSeconds = Math.round(durationDouble);
					String timeString = convertSecondsToTimeString(durationInSeconds);
					Time durationTime = Time.valueOf(timeString);
					
					int entityTracksSize = entity.getTracks().size();
					
					TrackDTO dto = new TrackDTO();
					dto.setTitle(name[i]);
					dto.setAlbumId(entity.getAlbumId());
					dto.setTrackNumber(Long.valueOf(i + entityTracksSize));
					dto.setDuration(durationTime);
					dto.setFilePath(sys_filename);
					dto.setViewCount(0L);
					dto.setWriter(writers[i]);
					dto.setReleaseDate(Instant.now());
					dto.setWriteId(entity.getArtistId());

					Track trackEntity = tMapper.toEntity(dto);
					Set<TrackTag> trackTag = new HashSet<>();
					
					// 해당 파일의 태그 처리
		            Long[] tagIdsArray = tagIdsForFiles.get(i);
		            for (Long tagId : tagIdsArray) {
		                MusicTags musicTag = musicReop.findById(tagId).orElse(null);
		                if (musicTag != null) {
		                    TrackTag tag = new TrackTag();
		                    tag.setMusicTags(musicTag);
		                    tag.setTrack(trackEntity);
		                    tagRepo.save(tag);
		                    trackTag.add(tag);
		                }
		            }

					trackEntity.setTrackTags(trackTag);
					savedTrack = tRepo.save(trackEntity);

//					set에 track값을 저장
					track.add(trackEntity);
					
					// 트랙 이미지 파일 처리
					if (titleImage != null) {
						
						TrackImageDTO imagedto = new TrackImageDTO();
						imagedto.setTrackId(savedTrack.getTrackId());
						imagedto.setImagePath(sys_imageName);
						imageRepo.save(imageMapper.toEntity(imagedto));
					}

					if (titleImage == null) {
						image_path[i] = "NULL";
						TrackImageDTO imagedto = new TrackImageDTO();
						imagedto.setTrackId(savedTrack.getTrackId());
						imagedto.setImagePath(image_path[i]);
						imageRepo.save(imageMapper.toEntity(imagedto));
					}

					File destFile = new File(uploadPath + File.separator + sys_filename);
					Muiscfile.transferTo(destFile);
				}
			}
		}
		
		if(deleteTrack!=null) {
			System.out.println("삭제된 트랙의 길이"+deleteTrack.length);
		}
		
		
//		트랙의 값 수정 여기를 제일 먼저 고쳐야 한다.
		Album updateEnttity=aRepo.findById(albumId).get();
		
		Set<Track> tracks =updateEnttity.getTracks();
		System.out.println("넌 얼마니 김"+tracks.size());
		System.out.println("albumsWriter에 길이: "+albumsWriter.length);
		System.out.println("Tracktitles에 길이: "+Tracktitles.length);
		// Iterate over the tracks and update writer and title
	    if(tracks.size() == albumsWriter.length && tracks.size() == Tracktitles.length) {
	        int index = 0;
	        for(Track track : tracks) {
	            track.setWriter(albumsWriter[index]);
	            track.setTitle(Tracktitles[index]);
	            tRepo.save(track); // Save each updated track
	            index++;
	        }
	    } else {
	        throw new IllegalArgumentException("Length of albumsWriter and Tracktitles arrays must match the number of tracks");
	    }
		
	    System.out.println("이거뜸?1");
		Set<AlbumWriter> albumWriters = new HashSet<>();

		for (int i = 0; i < albumsWriter.length; i++) {
//			모든 작성자를 삭제
			awRepo.deleteAllByAlbumWriterAlbumId(entity.getAlbumId());
			
			AlbumWriter writerNickname = new AlbumWriter();
			writerNickname.setAlbumId(entity.getAlbumId());
			writerNickname.setArtistNickname(albumsWriter[i]);

			awRepo.save(writerNickname);
			albumWriters.add(writerNickname);
		}
		
		 System.out.println("이거뜸?2");
		entity.setAlbumWriter(albumWriters);

		Album saveAlbum=aRepo.save(entity);
		
		AlbumDTO dto=aMapper.toDto(saveAlbum);
		
		return dto;
	}

	
//	===========================================
//	시간 변환
	public String convertSecondsToTimeString(long seconds) {
		long hours = seconds / 3600;
		long minutes = (seconds % 3600) / 60;
		long secs = seconds % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, secs);
	}
	
	
//	오래된 이미지 삭제
	public void deleteOldImage(String imagePath) {
	    if (imagePath != null && !imagePath.isEmpty()) {
	        File oldImageFile = new File("c:/tracks/image", imagePath);
	        if (oldImageFile.exists()) {
	            oldImageFile.delete();
	        }
	    }
	}
	
	public List<Long[]> extractTagIdsForFiles(MultiValueMap<String, String> trackTags, int fileCount) {
        List<Long[]> tagIdsForFiles = new ArrayList<>();

        for (int i = 0; i < fileCount; i++) {
            List<Long> tagIds = new ArrayList<>();
            for (String tagKey : trackTags.keySet()) {
                if (tagKey.startsWith("tags[" + i + "]")) {
                    tagIds.addAll(trackTags.get(tagKey).stream()
                                    .map(Long::parseLong)
                                    .collect(Collectors.toList()));
                }
            }
            Long[] tagIdsArray = tagIds.toArray(new Long[0]);
            tagIdsForFiles.add(tagIdsArray);
        }

        return tagIdsForFiles;
    }
	
//	태그 삽입하는 기능
	private Set<AlbumTag> createAlbumTags(Long[] albumTagIds, Album album) {
		atRepo.deleteAllByAlbumTagAlbumId(album.getAlbumId());
		
        Set<AlbumTag> albumTags = new HashSet<>();

        for (Long tagId : albumTagIds) {
            AlbumTagList albumTagList = atlRepo.findById(tagId).orElseThrow(
                () -> new RuntimeException("Album tag list not found for ID: " + tagId));
            AlbumTag albumTag = new AlbumTag();
            albumTag.setAlbumTagList(albumTagList);
            albumTag.setAlbum(album);

            atRepo.save(albumTag);
            albumTags.add(albumTag);
        }

        return albumTags;
    }
}
