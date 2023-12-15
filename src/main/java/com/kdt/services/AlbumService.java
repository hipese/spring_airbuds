package com.kdt.services;

import java.io.File;
import java.security.Principal;
import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
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
	    List<Long[]> tagIdsForFiles = new ArrayList<>();
	    for (int i = 0; i < files.length; i++) {
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
		Set<AlbumTag> albumtags = new HashSet<>();
		for (int i = 0; i < albumselectTag.length; i++) {
			AlbumTagList albumtaglist = atlRepo.findById(albumselectTag[i]).get();
			AlbumTag albumtag = new AlbumTag();
			albumtag.setAlbumTagList(albumtaglist);
			albumtag.setAlbum(savaAlum);

			atRepo.save(albumtag);
			albumtags.add(albumtag);
		}

//		엘범 제작자 저장
		Set<AlbumWriter> albumWriters = new HashSet<>();

		for (int i = 0; i < writers.length; i++) {

			AlbumWriter writerNickname = new AlbumWriter();
			writerNickname.setAlbumId(savaAlum.getAlbumId());
			writerNickname.setArtistNickname(writers[i]);

			awRepo.save(writerNickname);
			albumWriters.add(writerNickname);
		}

		entity.setAlbumTag(albumtags);
		entity.setAlbumWriter(albumWriters);

		aRepo.save(entity);
	}
	
	public List<AlbumDTO> selectByLoginId(Principal principal){
		String artist_id=principal.getName();
		
		List<Album> entity=aRepo.findAllByArtistIdStartingWith(artist_id);
		List<AlbumDTO> dtos=aMapper.toDtoList(entity);
		return dtos;
	}

	private String convertSecondsToTimeString(long seconds) {
		long hours = seconds / 3600;
		long minutes = (seconds % 3600) / 60;
		long secs = seconds % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, secs);
	}
}
