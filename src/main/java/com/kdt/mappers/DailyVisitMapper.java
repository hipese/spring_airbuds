package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.DailyVisit;
import com.kdt.dto.DailyVisitDTO;

@Mapper(componentModel = "spring")
public interface DailyVisitMapper extends GenericMapper<DailyVisitDTO, DailyVisit>{

}
