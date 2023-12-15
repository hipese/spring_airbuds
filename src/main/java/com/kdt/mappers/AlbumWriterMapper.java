package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.AlbumWriter;
import com.kdt.dto.AlbumWriterDTO;

@Mapper(componentModel = "spring")
public interface AlbumWriterMapper extends GenericMapper<AlbumWriterDTO, AlbumWriter> {

}
