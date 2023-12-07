package com.kdt.mappers;

import org.hibernate.annotations.Comment;
import org.mapstruct.Mapper;

import com.kdt.domain.entity.MusicTags;
import com.kdt.dto.MusicTagDTO;

@Mapper(componentModel = "spring")
public interface MusicTagMapper extends GenericMapper<MusicTagDTO, MusicTags> {

}
