package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.Playlist;
import com.kdt.dto.PlaylistDTO;

@Mapper(componentModel = "spring")
public interface PlaylistMapper extends GenericMapper<PlaylistDTO, Playlist>{

}
