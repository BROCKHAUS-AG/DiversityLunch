package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SexualityDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualityEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class SexualityMapper implements Mapper<SexualityDto, SexualityEntity> {
    @Override
    public SexualityDto entityToDto(SexualityEntity entity) {
        SexualityDto sexualityDto = new SexualityDto();
        sexualityDto.setId(entity.getId());
        sexualityDto.setDescriptor(entity.getDescriptor());
        return sexualityDto;
    }

    @Override
    public SexualityEntity dtoToEntity(SexualityDto dto) {
        SexualityEntity sexualityEntity = new SexualityEntity();
        sexualityEntity.setId(dto.getId());
        sexualityEntity.setDescriptor(dto.getDescriptor());
        return sexualityEntity;
    }

    @Override
    public List<SexualityDto> entityToDto(List<SexualityEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<SexualityEntity> dtoToEntity(List<SexualityDto> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
