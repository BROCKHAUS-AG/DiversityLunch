package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProjectDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProjectEntity;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements DimensionMapper<ProjectDto, ProjectEntity> {

    @Override
    public ProjectDto entityToDto(ProjectEntity entity) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(entity.getId());
        projectDto.setDescriptor(entity.getDescriptor());
        projectDto.setDefault(entity.isDefault());
        return projectDto;
    }

    @Override
    public ProjectEntity dtoToEntity(ProjectDto dto) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(dto.getId());
        projectEntity.setDescriptor(dto.getDescriptor());
        projectEntity.setDefault(dto.isDefault());
        return projectEntity;
    }
}
