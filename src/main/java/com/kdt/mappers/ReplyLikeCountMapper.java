package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.ReplyLikeCount;
import com.kdt.dto.ReplyLikeCountDTO;

@Mapper(componentModel = "spring")
public interface ReplyLikeCountMapper extends GenericMapper<ReplyLikeCountDTO, ReplyLikeCount>{

}
