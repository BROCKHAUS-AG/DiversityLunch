package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SexualOrientationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualOrientationEntity;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;


@Component
public class SexualOrientationMapper implements Mapper<SexualOrientationDto, SexualOrientationEntity> {
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

    @Override
    public List<SexualOrientationDto> entityToDto(List<SexualOrientationEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<SexualOrientationEntity> dtoToEntity(List<SexualOrientationDto> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
