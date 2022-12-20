package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SexualOrientationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualOrientationEntity;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionMapper;
import org.springframework.stereotype.Component;


@Component
public class SexualOrientationMapper implements DimensionMapper<SexualOrientationDto, SexualOrientationEntity> {
    @Override
    public SexualOrientationDto entityToDto(SexualOrientationEntity entity) {
        SexualOrientationDto sexualOrientationDto = new SexualOrientationDto();
        sexualOrientationDto.setId(entity.getId());
        sexualOrientationDto.setDescriptor(entity.getDescriptor());
        return sexualOrientationDto;
    }

    @Override
    public SexualOrientationEntity dtoToEntity(SexualOrientationDto dto) {
        SexualOrientationEntity sexualOrientationEntity = new SexualOrientationEntity();
        sexualOrientationEntity.setId(dto.getId());
        sexualOrientationEntity.setDescriptor(dto.getDescriptor());
        return sexualOrientationEntity;
    }
}
