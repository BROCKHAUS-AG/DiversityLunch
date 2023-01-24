package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SocialBackgroundDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SocialBackgroundEntity;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SocialBackgroundMapper implements DimensionMapper<SocialBackgroundDto, SocialBackgroundEntity> {
    @Override
    public SocialBackgroundDto entityToDto(SocialBackgroundEntity entity) {
        SocialBackgroundDto socialBackgroundDto = new SocialBackgroundDto();
        socialBackgroundDto.setId(entity.getId());
        socialBackgroundDto.setDescriptor(entity.getDescriptor());
        socialBackgroundDto.setDefault(entity.isDefault());
        return socialBackgroundDto;
    }

    @Override
    public SocialBackgroundEntity dtoToEntity(SocialBackgroundDto dto) {
        SocialBackgroundEntity socialBackgroundEntity = new SocialBackgroundEntity();
        socialBackgroundEntity.setId(dto.getId());
        socialBackgroundEntity.setDescriptor(dto.getDescriptor());
        socialBackgroundEntity.setDefault(dto.isDefault());
        return socialBackgroundEntity;
    }
}
