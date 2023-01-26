package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HobbyMapper implements DimensionMapper<HobbyDto, HobbyEntity> {

    @Override
    public HobbyDto entityToDto(HobbyEntity entity) {
        HobbyDto dto = new HobbyDto();
        dto.setId(entity.getId());
        dto.setDescriptor(entity.getDescriptor());
        return dto;
    }

    @Override
    public HobbyEntity dtoToEntity(HobbyDto dto) {
        HobbyEntity entity = new HobbyEntity();
        entity.setId(dto.getId());
        entity.setDescriptor(dto.getDescriptor());
        return entity;
    }
}

