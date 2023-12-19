package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.FollowingSingerView;
import com.kdt.dto.FollowingSingerViewDTO;

@Mapper(componentModel = "spring")
public interface FollowingSingerViewMapper extends GenericMapper<FollowingSingerViewDTO, FollowingSingerView>{

}
