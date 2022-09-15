package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.profile.modelTest.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.modelTest.entities.DietEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<DietDto> entityToDto(List<DietEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<DietEntity> dtoToEntity(List<DietDto> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
