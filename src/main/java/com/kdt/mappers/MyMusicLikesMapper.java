package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.MyMusicLikes;
import com.kdt.dto.MyMusicLikesDTO;

@Mapper(componentModel = "spring")
public interface MyMusicLikesMapper extends GenericMapper<MyMusicLikesDTO, MyMusicLikes>{

}
