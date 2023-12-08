package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.Qna;
import com.kdt.dto.QnaDTO;

@Mapper(componentModel = "spring")
public interface QnaMapper extends GenericMapper<QnaDTO, Qna>{

}
