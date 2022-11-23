package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.WorkExperienceDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.WorkExperienceEntity;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class WorkExperienceMapper implements Mapper<WorkExperienceDto, WorkExperienceEntity> {
    @Override
    public WorkExperienceDto entityToDto(WorkExperienceEntity entity) {
        WorkExperienceDto workExperienceDto = new WorkExperienceDto();
        workExperienceDto.setId(entity.getId());
        workExperienceDto.setDescriptor(entity.getDescriptor());
        return workExperienceDto;
    }

    @Override
    public WorkExperienceEntity dtoToEntity(WorkExperienceDto dto) {
        WorkExperienceEntity workExperienceEntity = new WorkExperienceEntity();
        workExperienceEntity.setId(dto.getId());
        workExperienceEntity.setDescriptor(dto.getDescriptor());
        return workExperienceEntity;
    }
}
