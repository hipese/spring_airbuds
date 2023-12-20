package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.SanctionView;
import com.kdt.dto.SanctionViewDTO;

@Mapper(componentModel = "spring")
public interface SanctionViewMapper extends GenericMapper<SanctionViewDTO, SanctionView>{

}
