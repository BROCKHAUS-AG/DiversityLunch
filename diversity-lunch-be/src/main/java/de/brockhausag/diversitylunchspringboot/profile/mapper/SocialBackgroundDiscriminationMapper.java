package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SocialBackgroundDiscriminationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SocialBackgroundDiscriminationEntity;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionMapper;
import org.springframework.stereotype.Component;

@Component
public class SocialBackgroundDiscriminationMapper implements DimensionMapper<SocialBackgroundDiscriminationDto, SocialBackgroundDiscriminationEntity> {

    @Override
    public SocialBackgroundDiscriminationDto entityToDto(SocialBackgroundDiscriminationEntity entity) {
        SocialBackgroundDiscriminationDto discriminationDto = new SocialBackgroundDiscriminationDto();
        discriminationDto.setId(entity.getId());
        discriminationDto.setDescriptor(entity.getDescriptor());
        return discriminationDto;
    }

    @Override
    public SocialBackgroundDiscriminationEntity dtoToEntity(SocialBackgroundDiscriminationDto dto) {
        SocialBackgroundDiscriminationEntity discriminationEntity = new SocialBackgroundDiscriminationEntity();
        discriminationEntity.setId(dto.getId());
        discriminationEntity.setDescriptor(dto.getDescriptor());
        return discriminationEntity;
    }
}
