package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HobbyMapper implements Mapper<HobbyDto, HobbyEntity> {

    private final HobbyCategoryMapper categoryMapper = new HobbyCategoryMapper();


    @Override
    public HobbyDto entityToDto(HobbyEntity entity) {
        HobbyDto dto = new HobbyDto();
        dto.setId(entity.getId());
        dto.setDescriptor(entity.getDescriptor());
        dto.setCategory(categoryMapper.entityToDto(entity.getCategory()));

        return dto;
    }

    @Override
    public HobbyEntity dtoToEntity(HobbyDto dto) {
        HobbyEntity entity = new HobbyEntity();
        entity.setId(dto.getId());
        entity.setDescriptor(dto.getDescriptor());
        entity.setCategory(categoryMapper.dtoToEntity(dto.getCategory()));
        return entity;
    }

    @Override
    public List<HobbyDto> entityToDto(List<HobbyEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<HobbyEntity> dtoToEntity(List<HobbyDto> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
