package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.TrackReplyLike;
import com.kdt.dto.TrackReplyLikeDTO;

@Mapper(componentModel = "spring")
public interface TrackReplyLikeMapper extends GenericMapper<TrackReplyLikeDTO, TrackReplyLike>{

}
