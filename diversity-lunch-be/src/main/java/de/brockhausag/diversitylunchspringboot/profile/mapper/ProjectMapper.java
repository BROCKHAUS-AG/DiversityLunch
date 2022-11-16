package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProjectDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProjectEntity;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper implements Mapper<ProjectDto, ProjectEntity> {

    @Override
    public ProjectDto entityToDto(ProjectEntity entity) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(entity.getId());
        projectDto.setDescriptor(entity.getDescriptor());
        return projectDto;
    }

    @Override
    public ProjectEntity dtoToEntity(ProjectDto dto) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(dto.getId());
        projectEntity.setDescriptor(dto.getDescriptor());
        return projectEntity;
    }

    @Override
    public List<ProjectDto> entityToDto(List<ProjectEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProjectEntity> dtoToEntity(List<ProjectDto> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
