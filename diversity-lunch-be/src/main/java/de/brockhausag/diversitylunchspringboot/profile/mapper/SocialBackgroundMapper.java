package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SocialBackgroundDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SocialBackgroundEntity;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SocialBackgroundMapper implements Mapper<SocialBackgroundDto, SocialBackgroundEntity> {
    @Override
    public SocialBackgroundDto entityToDto(SocialBackgroundEntity entity) {
        SocialBackgroundDto socialBackgroundDto = new SocialBackgroundDto();
        socialBackgroundDto.setId(entity.getId());
        socialBackgroundDto.setDescriptor(entity.getDescriptor());
        return socialBackgroundDto;
    }

    @Override
    public SocialBackgroundEntity dtoToEntity(SocialBackgroundDto dto) {
        SocialBackgroundEntity socialBackgroundEntity = new SocialBackgroundEntity();
        socialBackgroundEntity.setId(dto.getId());
        socialBackgroundEntity.setDescriptor(dto.getDescriptor());
        return socialBackgroundEntity;
    }
}
