package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.WorkExperienceDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.WorkExperienceEntity;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<WorkExperienceDto> entityToDto(List<WorkExperienceEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<WorkExperienceEntity> dtoToEntity(List<WorkExperienceDto> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
