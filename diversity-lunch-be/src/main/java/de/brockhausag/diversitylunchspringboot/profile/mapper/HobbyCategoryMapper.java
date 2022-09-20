package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyCategoryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyCategoryEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HobbyCategoryMapper implements Mapper<HobbyCategoryDto, HobbyCategoryEntity> {
    @Override
    public HobbyCategoryDto entityToDto(HobbyCategoryEntity entity) {
        HobbyCategoryDto hobbyCategoryDto = new HobbyCategoryDto();
        hobbyCategoryDto.setId(entity.getId());
        hobbyCategoryDto.setDescriptor(entity.getDescriptor());
        return hobbyCategoryDto;
    }

    @Override
    public HobbyCategoryEntity dtoToEntity(HobbyCategoryDto dto) {
        HobbyCategoryEntity hobbyCategoryEntity = new HobbyCategoryEntity();
        hobbyCategoryEntity.setId(dto.getId());
        hobbyCategoryEntity.setDescriptor(dto.getDescriptor());
        return hobbyCategoryEntity;
    }

    @Override
    public List<HobbyCategoryDto> entityToDto(List<HobbyCategoryEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<HobbyCategoryEntity> dtoToEntity(List<HobbyCategoryDto> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
