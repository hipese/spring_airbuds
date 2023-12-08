package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.QnaAnswer;
import com.kdt.dto.QnaAnswerDTO;

@Mapper(componentModel = "spring")
public interface QnaAnswerMapper extends GenericMapper<QnaAnswerDTO, QnaAnswer>{

}
