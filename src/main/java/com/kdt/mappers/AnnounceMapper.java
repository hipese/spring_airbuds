package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.AnnounceBoard;
import com.kdt.dto.AnnounceBoardDTO;

@Mapper(componentModel = "spring")
public interface AnnounceMapper extends GenericMapper<AnnounceBoardDTO, AnnounceBoard>{

}
