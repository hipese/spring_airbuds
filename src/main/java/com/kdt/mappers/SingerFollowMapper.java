package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.SingerFollow;
import com.kdt.dto.SingerFollowDTO;

@Mapper(componentModel = "spring")
public interface SingerFollowMapper extends GenericMapper<SingerFollowDTO, SingerFollow>{

}
