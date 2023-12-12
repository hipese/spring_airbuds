
package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entity.QnaFile;
import com.kdt.dto.QnaFileDTO;

@Mapper(componentModel = "spring")
public interface QnaFileMapper extends GenericMapper<QnaFileDTO, QnaFile>{

}
