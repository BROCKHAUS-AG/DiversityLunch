package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

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
}
