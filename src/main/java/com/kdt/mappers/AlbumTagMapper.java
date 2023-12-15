package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.AlbumTag;
import com.kdt.dto.AlbumTagDTO;

@Mapper(componentModel = "spring")
public interface AlbumTagMapper extends GenericMapper<AlbumTagDTO, AlbumTag> {

}
