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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.controllers.TrackController;
import com.kdt.domain.entity.Album;
import com.kdt.domain.entity.AlbumTag;
import com.kdt.domain.entity.AlbumTagList;
import com.kdt.domain.entity.AlbumWriter;
import com.kdt.domain.entity.MusicTags;
import com.kdt.domain.entity.Track;
import com.kdt.domain.entity.TrackImages;
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

	private static final Logger logger=LoggerFactory.getLogger(AlbumService.class); 
	
	@Transactional
	public void insertAlbum(MultipartFile[] files, String[] name, String[] durations, String[] image_path,
			String releaseDate, MultipartFile titleImage, String[] writers, Long[] albumselectTag, Long[] order,
			String albumTitle, String loginId, MultiValueMap<String, String> trackTags) throws Exception {

		File imagePath = new File("/tracks/image");
		File uploadPath = new File("/tracks");

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
				dto.setBan(0L);

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

	public List<AlbumDTO> selectByLoginId(Principal principal) {
		String artist_id = principal.getName();

		List<Album> entity = aRepo.findAllByArtistIdStartingWith(artist_id);
		List<AlbumDTO> dtos = aMapper.toDtoList(entity);
		return dtos;
	}

	@Transactional
	public AlbumDTO updateAlbum(MultipartFile[] files, String[] name, String[] durations, String[] writers,
			String[] image_path, MultipartFile titleImage, Long[] albumselectTag, String albumTitle,
			String[] albumsWriter, String[] Tracktitles, String prevImage, Long[] deleteTrack,
			MultiValueMap<String, String> trackTags, Long albumId) throws Exception {

//		앨범 변경
		Album entity = aRepo.findById(albumId).get();
		File imagePath = new File("/tracks/image");
		entity.setTitle(albumTitle);
		String sys_imageName = null;
		
//		이미지가 변경되었을 시 동작이미지가 있으면 경로 변경
		if (titleImage != null) {
			sys_imageName = UUID.randomUUID().toString() + "_" + titleImage.getOriginalFilename();

			logger.info("바꿔야할 이미지 사진의 이름: " + sys_imageName);
			// Save new image
			if (!imagePath.exists()) {
				imagePath.mkdir();
			}
			File newImageFile = new File(imagePath, sys_imageName);
			titleImage.transferTo(newImageFile);
			// Delete old image file
			deleteOldImage(prevImage);

		}else {
			sys_imageName=prevImage;
			
		}
		
		entity.setCoverImagePath(sys_imageName);

	

//		엘범 테그 설정하는 기능
		Set<AlbumTag> albumTags = createAlbumTags(albumselectTag, entity);
		entity.setAlbumTag(albumTags);

//		만약 기존앨범의 트랙을 삭제했으면 삭제된 트랙의 albumId를 초기화
		if (deleteTrack != null) {
			logger.debug("deleteTrack몇개냐?: " + deleteTrack.length);
			logger.debug("deleteTrack뭔갑?: " + deleteTrack[0]);
			for (int i = 0; i < deleteTrack.length; i++) {
				Track notalbumTrack = tRepo.findById(deleteTrack[i])
						.orElseThrow(() -> new RuntimeException("Track not found"));
				notalbumTrack.setPrevAlbumId(albumId);
				notalbumTrack.setAlbumId(null);
				tRepo.save(notalbumTrack);
			}
			// Flush changes to ensure they are reflected in the database
			tRepo.flush();
		}

//		트랙의 값 수정 여기를 제일 먼저 고쳐야 한다.
		Album updatedAlbum = aRepo.findById(albumId).orElseThrow(() -> new RuntimeException("Album not found"));

		Set<Track> updatedTracks = updatedAlbum.getTracks();
		logger.debug("예전 트랙 지워졌냐? : " + updatedTracks.size());

		// Iterate over the tracks and update writer and title 삭제된 트랙수에 따라 다르게 설정하게 한다.
		if (deleteTrack == null) {
			if (updatedTracks.size() == albumsWriter.length && updatedTracks.size() == Tracktitles.length) {
				int index = 0;
				for (Track track : updatedTracks) {
					track.setWriter(albumsWriter[index]);
					track.setTitle(Tracktitles[index]);
					tRepo.save(track); // Save each updated track
					index++;
				}
			} else {
				throw new IllegalArgumentException(
						"Length of albumsWriter and Tracktitles arrays must match the number of tracks");
			}
		} else {
			if (updatedTracks.size() == albumsWriter.length && updatedTracks.size() == Tracktitles.length) {
				int index = 0;
				for (Track track : updatedTracks) {
					track.setWriter(albumsWriter[index]);
					track.setTitle(Tracktitles[index]);
					tRepo.save(track); // Save each updated track
					index++;
				}
			} else {
				throw new IllegalArgumentException(
						"Length of albumsWriter and Tracktitles arrays must match the number of tracks");
			}
		}

//		기존 트랙의 값을 가지는 set
		Set<Track> existingTracks = new HashSet<>(entity.getTracks());
		
//		기존에 존재하던 트랙에 이미지 경로를 재설정
		for (Track track : existingTracks) {
			TrackImages image =imageRepo.findByTrackImagesTrackId(track.getTrackId());
			if (image != null) {
		        // 기존의 트랙에 imagePath 업데이트
				logger.info("기존에 이미지경로 셋팅~: "+sys_imageName);
		        image.setImagePath(sys_imageName);
		        imageRepo.save(image); // 변경 사항을 데이터베이스에 저장
		    }
		}
		
//		트랙을 삽입하는 로직 작성
		if (files != null) {

			// 각 파일별 태그 ID 배열 생성
			List<Long[]> tagIdsForFiles = extractTagIdsForFiles(trackTags, files.length);

			File uploadPath = new File("/tracks");
			
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
					dto.setBan(0L);

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
					
//					새로 삽입하는 파일의 이미지 경로 랜더링을 다시한다.
					// 트랙 이미지 파일 처리
					if (titleImage != null) {
						TrackImageDTO imagedto = new TrackImageDTO();
						imagedto.setTrackId(savedTrack.getTrackId());
						imagedto.setImagePath(sys_imageName);
						logger.info("이미지 뭐로 설정함? "+sys_imageName);
						imageRepo.save(imageMapper.toEntity(imagedto));
					}else {
						image_path[i] = prevImage;
						TrackImageDTO imagedto = new TrackImageDTO();
						imagedto.setTrackId(savedTrack.getTrackId());
						imagedto.setImagePath(image_path[i]);
						imageRepo.save(imageMapper.toEntity(imagedto));
						
					}

					File destFile = new File(uploadPath + File.separator + sys_filename);
					Muiscfile.transferTo(destFile);
				}
			}
			existingTracks.addAll(track);
			entity.setTracks(existingTracks);
		}

		Album saveAlbum = aRepo.save(entity);

		AlbumDTO dto = aMapper.toDto(saveAlbum);

		return dto;
	}
	
//	기능 만들려다가 포기한 상태임
	@Transactional
	public AlbumDTO emptyAlbum(Principal principal) {
		AlbumDTO dto=new AlbumDTO();
		dto.setArtistId(principal.getName());
		dto.setCoverImagePath(null);
		dto.setTitle("익명의 앨범");
		dto.setReleaseDate(Instant.now());
		
		Album entity = aMapper.toEntity(dto);
	    Album savedEntity = aRepo.save(entity);
	    
	    AlbumDTO realdto=aMapper.toDto(savedEntity);
	    
		return realdto;
	}
	
//	내 앨범에 편집 기능을 사용할 수 있는지 아닌지 확인하는 부분
	public boolean isEditAlbum(Principal principal,String artistId) {
		
		if(principal==null) {
			return false;
		}
		
		if(principal.getName().equals(artistId)) {
			return true;
		}
		
		return false;
	}
	
	public List<AlbumDTO> profileAlbum(String artistId){
		List<Album> entity=aRepo.findAllByArtistIdStartingWith(artistId);
		List<AlbumDTO> dtos=aMapper.toDtoList(entity);
		return dtos;
	}
	
	public AlbumDTO findByAlbumId(Long albumId) {
		Album entity=aRepo.findById(albumId).get();
		AlbumDTO dto=aMapper.toDto(entity);
		return dto;
	}
	
	public List<AlbumDTO> searchAlbumByText(String searchText){
		List<Album> entity=aRepo.findAllByTitleStartingWith(searchText);
		List<AlbumDTO> dto=aMapper.toDtoList(entity);
		return dto;
	}
	
	
	@Transactional
	public void deleteAlbum(long albumId) {

		Album entity = aRepo.findById(albumId).get();

//		오래된 이미지 삭제

//		여기서 트랙 삭제할때 내부에 tag, 이미지 전부 삭제해야 하는지 찾아보자
		if (entity != null) {
			// albumId과 일치하는 테그를 전부 삭제한다.
			atRepo.deleteAllByAlbumTagAlbumId(albumId);

//			예전 이미지를 삭제
			deleteOldImage(entity.getCoverImagePath());

//			각각의 트랙들과 관련된 정보들을 삭제한다
			Set<Track> albumtracks = entity.getTracks();
			for (Track track : albumtracks) {

//				트랙에 존재라는 이미지 삭제
				TrackImages imageEntity = imageRepo.findByTrackImagesTrackId(track.getTrackId());
				String imagePath = "/tracks/image" + File.separator + imageEntity.getImagePath();
				File imageToDelete = new File(imagePath);

				if (imageToDelete.exists()) {
					imageToDelete.delete();
					imageRepo.deleteById(imageEntity.getTrackId());// 데이터베이스에서 이미지 삭제

				} else {
					logger.error("파일이 존재하지 않습니다: " + imagePath);
				}

				// 각각의 트랙에서 track_id과 일치하는 테그를 전부 삭제한다.
				tagRepo.deleteAllByTrackTagTrackId(track.getTrackId());

//				트랙에서 음원이 존재하면 전부 삭제한다.
				String filePath = "/tracks" + File.separator + track.getFilePath();
				File fileToDelete = new File(filePath);

				if (fileToDelete.exists()) {
					fileToDelete.delete(); // 실제 경로 삭제
					tRepo.deleteById(track.getTrackId()); // 데이터베이스에서 삭제
				} else {
					logger.error("파일이 존재하지 않습니다: " + filePath);
				}
//				각 트랙을 삭제하는 함수
				tRepo.deleteById(track.getTrackId());
			}

			awRepo.deleteAllByAlbumWriterAlbumId(albumId);
			aRepo.deleteById(albumId);

		}

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
			File oldImageFile = new File("/tracks/image", imagePath);
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
					tagIds.addAll(trackTags.get(tagKey).stream().map(Long::parseLong).collect(Collectors.toList()));
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
			AlbumTagList albumTagList = atlRepo.findById(tagId)
					.orElseThrow(() -> new RuntimeException("Album tag list not found for ID: " + tagId));
			AlbumTag albumTag = new AlbumTag();
			albumTag.setAlbumTagList(albumTagList);
			albumTag.setAlbum(album);

			atRepo.save(albumTag);
			albumTags.add(albumTag);
		}
		return albumTags;
	}
}
