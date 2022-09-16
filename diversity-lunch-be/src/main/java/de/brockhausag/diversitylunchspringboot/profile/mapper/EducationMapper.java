package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EducationMapper implements Mapper<EducationDto, EducationEntity> {
    @Override
    public EducationDto entityToDto(EducationEntity entity) {
        EducationDto educationDto = new EducationDto();
        educationDto.setId(entity.getId());
        educationDto.setDescriptor(entity.getDescriptor());
        return educationDto;
    }

    @Override
    public EducationEntity dtoToEntity(EducationDto dto) {
        EducationEntity educationEntity = new EducationEntity();
        educationEntity.setId(dto.getId());
        educationEntity.setDescriptor(dto.getDescriptor());
        return educationEntity;
    }

    @Override
    public List<EducationDto> entityToDto(List<EducationEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<EducationEntity> dtoToEntity(List<EducationDto> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
