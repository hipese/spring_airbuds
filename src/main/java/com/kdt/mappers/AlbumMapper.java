package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.Album;
import com.kdt.dto.AlbumDTO;

@Mapper(componentModel = "spring")
public interface AlbumMapper extends GenericMapper<AlbumDTO, Album>{

}
