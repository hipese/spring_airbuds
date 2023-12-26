package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.HistoryLikeView;
import com.kdt.dto.HistoryLikeViewDTO;

@Mapper(componentModel = "spring")
public interface HistoryLikeViewMapper extends GenericMapper<HistoryLikeViewDTO, HistoryLikeView>{

}
