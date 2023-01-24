package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ReligionDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ReligionEntity;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionMapper;
import org.springframework.stereotype.Component;

@Component
public class ReligionMapper implements DimensionMapper<ReligionDto, ReligionEntity> {

    @Override
    public ReligionDto entityToDto(ReligionEntity entity) {
        ReligionDto religionDto = new ReligionDto();
        religionDto.setId(entity.getId());
        religionDto.setDescriptor(entity.getDescriptor());
        religionDto.setDefault(entity.isDefault());
        return religionDto;
    }

    @Override
    public ReligionEntity dtoToEntity(ReligionDto dto) {
        ReligionEntity religionEntity = new ReligionEntity();
        religionEntity.setId(dto.getId());
        religionEntity.setDescriptor(dto.getDescriptor());
        religionEntity.setDefault(dto.isDefault());
        return religionEntity;
    }
}
