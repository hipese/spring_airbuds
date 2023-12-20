package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.DailyStreaming;
import com.kdt.dto.DailyStreamingDTO;

@Mapper(componentModel = "spring")
public interface DailyStreamingMapper extends GenericMapper<DailyStreamingDTO, DailyStreaming>{

}
