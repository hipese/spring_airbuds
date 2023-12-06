package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.Track;
import com.kdt.dto.TrackDTO;


@Mapper(componentModel = "spring")
public interface TrackMapper extends GenericMapper<TrackDTO, Track>{

}
