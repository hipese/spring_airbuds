package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.CurrentPlayList;
import com.kdt.dto.CurrentPlayListDTO;

@Mapper(componentModel = "spring")
public interface CurrentPlayListMapper extends GenericMapper<CurrentPlayListDTO, CurrentPlayList>{

}
