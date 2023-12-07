package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.TrackTag;
import com.kdt.dto.TrackTagDTO;

@Mapper(componentModel = "spring")
public interface TrackTagMapper extends GenericMapper<TrackTagDTO, TrackTag> {

}
