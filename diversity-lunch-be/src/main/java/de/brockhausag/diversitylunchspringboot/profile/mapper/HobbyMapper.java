package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class HobbyMapper implements Mapper<HobbyDto, HobbyEntity> {

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

