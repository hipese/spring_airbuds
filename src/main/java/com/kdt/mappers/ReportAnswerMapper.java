package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.ReportAnswer;
import com.kdt.dto.ReportAnswerDTO;

@Mapper(componentModel = "spring")
public interface ReportAnswerMapper extends GenericMapper<ReportAnswerDTO, ReportAnswer>{

}
