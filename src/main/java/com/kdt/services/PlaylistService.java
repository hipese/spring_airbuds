package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdt.domain.entity.Playlist;
import com.kdt.domain.entity.PlaylistTrack;
import com.kdt.dto.PlaylistDTO;
import com.kdt.mappers.PlaylistMapper;
import com.kdt.mappers.PlaylistTrackMapper;
import com.kdt.repositories.PlaylistRepository;
import com.kdt.repositories.PlaylistTrackRepository;

@Service
public class PlaylistService {

	@Autowired
	private PlaylistRepository plRepo;
	
	@Autowired
	private PlaylistTrackRepository pltRepo;
	
	@Autowired
	private PlaylistMapper plMapper;
	
	@Autowired
	private PlaylistTrackMapper pltMapper;
	
	@Transactional
	public void insert(PlaylistDTO dto) {
		Playlist pl = plMapper.toEntity(dto);
		Long playlistSeq = plRepo.save(pl).getPlaylistSeq();
		List<PlaylistTrack> pltList = pltMapper.toEntityList(dto.getPlaylistTrack());
		for(PlaylistTrack plt : pltList) {
			plt.setPlaylistParentSeq(playlistSeq);
		}
		pltRepo.saveAll(pltList);
	}
	
	public void insertPlaylist(PlaylistDTO dto) {
	    List<PlaylistTrack> pltList = pltMapper.toEntityList(dto.getPlaylistTrack());
	    pltRepo.saveAll(pltList);
	}
	
	public List<PlaylistDTO> selectAll(String id) {
		List<Playlist> list = plRepo.findByPlaylistWriteId(id);
		List<PlaylistDTO> dtoList = plMapper.toDtoList(list);
		return dtoList;
	}
}
