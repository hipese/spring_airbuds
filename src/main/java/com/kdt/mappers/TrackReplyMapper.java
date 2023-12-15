package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.TrackReply;
import com.kdt.dto.TrackReplyDTO;

@Mapper(componentModel = "spring")
public interface TrackReplyMapper extends GenericMapper<TrackReplyDTO, TrackReply>{

}
