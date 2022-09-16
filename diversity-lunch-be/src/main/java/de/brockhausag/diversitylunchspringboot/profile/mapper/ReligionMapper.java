package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ReligionDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ReligionEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReligionMapper implements Mapper<ReligionDto, ReligionEntity> {

    @Override
    public ReligionDto entityToDto(ReligionEntity entity) {
        ReligionDto religionDto = new ReligionDto();
        religionDto.setId(entity.getId());
        religionDto.setDescriptor(entity.getDescriptor());
        return religionDto;
    }

    @Override
    public ReligionEntity dtoToEntity(ReligionDto dto) {
        ReligionEntity religionEntity = new ReligionEntity();
        religionEntity.setId(dto.getId());
        religionEntity.setDescriptor(dto.getDescriptor());
        return religionEntity;
    }

    @Override
    public List<ReligionDto> entityToDto(List<ReligionEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ReligionEntity> dtoToEntity(List<ReligionDto> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}