package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.PlaylistTrack;
import com.kdt.dto.PlaylistTrackDTO;

@Mapper(componentModel = "spring")
public interface PlaylistTrackMapper extends GenericMapper<PlaylistTrackDTO, PlaylistTrack>{
	
}
