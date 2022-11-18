package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.DietEntity;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class DietMapper implements Mapper<DietDto, DietEntity> {

    @Override
    public DietDto entityToDto(DietEntity entity) {
        DietDto dietDto = new DietDto();
        dietDto.setId(entity.getId());
        dietDto.setDescriptor(entity.getDescriptor());
        return dietDto;
    }

    @Override
    public DietEntity dtoToEntity(DietDto dto) {
        DietEntity dietEntity = new DietEntity();
        dietEntity.setId(dto.getId());
        dietEntity.setDescriptor(dto.getDescriptor());
        return dietEntity;
    }
}
