package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.MemberAgeView;
import com.kdt.dto.MemberAgeViewDTO;

@Mapper(componentModel = "spring")
public interface MemberAgeViewMapper extends GenericMapper<MemberAgeViewDTO, MemberAgeView>{

}
