package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.LikeTrackView;
import com.kdt.dto.LikeTrackViewDTO;

@Mapper(componentModel = "spring")
public interface LikeTrackViewMapper extends GenericMapper<LikeTrackViewDTO, LikeTrackView>{

}
