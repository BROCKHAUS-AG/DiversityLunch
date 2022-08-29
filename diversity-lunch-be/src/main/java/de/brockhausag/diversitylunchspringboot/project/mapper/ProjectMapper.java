package de.brockhausag.diversitylunchspringboot.project.mapper;

import de.brockhausag.diversitylunchspringboot.project.model.ProjectDto;
import de.brockhausag.diversitylunchspringboot.project.model.ProjectEntity;


public interface ProjectMapper {
    ProjectEntity mapCreateDtoToEntity(ProjectDto projectDto);

    ProjectEntity mapDtoToEntity(ProjectDto profileDto);

    ProjectDto mapEntityToDto(ProjectEntity entity);
}
