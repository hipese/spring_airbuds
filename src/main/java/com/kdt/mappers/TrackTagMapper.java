package com.kdt.mappers;



import org.mapstruct.Mapper;

import com.kdt.domain.entity.MusicTags;
import com.kdt.domain.entity.TrackTag;
import com.kdt.dto.MusicTagDTO;
import com.kdt.dto.TrackTagDTO;

@Mapper(componentModel = "spring")
public interface TrackTagMapper extends GenericMapper<TrackTagDTO, TrackTag> {
	
	 // MusicTagDTO를 MusicTags 엔티티로 변환
    default MusicTags musicTagDTOToMusicTags(MusicTagDTO dto) {
        if (dto == null) {
            return null;
        }

        MusicTags musicTags = new MusicTags();
        musicTags.setTagId(dto.getTagId());
        musicTags.setTagName(dto.getTagName());
        // 여기에 필요한 다른 필드 변환 로직 추가

        return musicTags;
    }

    // MusicTags 엔티티를 MusicTagDTO로 변환
    default MusicTagDTO musicTagsToMusicTagDTO(MusicTags entity) {
        if (entity == null) {
            return null;
        }

        MusicTagDTO dto = new MusicTagDTO();
        dto.setTagId(entity.getTagId());
        dto.setTagName(entity.getTagName());
        // 여기에 필요한 다른 필드 변환 로직 추가

        return dto;
    }

    // TrackTagDTO와 TrackTag 간의 전체 변환
    @Override
    TrackTag toEntity(TrackTagDTO dto);

    @Override
    TrackTagDTO toDto(TrackTag entity);
	
}
