package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.MusicLike;
import com.kdt.dto.MusicLikeDTO;

@Mapper(componentModel = "spring")
public interface MusicLikeMapper extends GenericMapper<MusicLikeDTO, MusicLike>{

}
