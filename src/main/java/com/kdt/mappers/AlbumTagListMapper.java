package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.AlbumTagList;
import com.kdt.dto.AlbumTagListDTO;

@Mapper(componentModel = "spring")
public interface AlbumTagListMapper extends GenericMapper<AlbumTagListDTO, AlbumTagList> {

}
