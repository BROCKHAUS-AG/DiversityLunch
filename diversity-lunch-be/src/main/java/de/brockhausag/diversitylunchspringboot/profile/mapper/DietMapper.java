package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.DietEntity;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionMapper;
import org.springframework.stereotype.Component;

@Component
public class DietMapper implements DimensionMapper<DietDto, DietEntity> {

    @Override
    public DietDto entityToDto(DietEntity entity) {
        DietDto dietDto = new DietDto();
        dietDto.setId(entity.getId());
        dietDto.setDescriptor(entity.getDescriptor());
        dietDto.setDefault(entity.isDefault());
        return dietDto;
    }

    @Override
    public DietEntity dtoToEntity(DietDto dto) {
        DietEntity dietEntity = new DietEntity();
        dietEntity.setId(dto.getId());
        dietEntity.setDescriptor(dto.getDescriptor());
        dietEntity.setDefault(dto.isDefault());
        return dietEntity;
    }
}
