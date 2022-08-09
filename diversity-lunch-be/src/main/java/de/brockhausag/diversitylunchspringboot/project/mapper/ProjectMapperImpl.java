package de.brockhausag.diversitylunchspringboot.project.mapper;

import de.brockhausag.diversitylunchspringboot.project.model.ProjectDto;
import de.brockhausag.diversitylunchspringboot.project.model.ProjectEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProjectMapperImpl implements ProjectMapper{

    private final ModelMapper modelMapper;

    @Override
    public ProjectEntity mapCreateDtoToEntity(final ProjectDto projectDto) {
        return this.modelMapper.map(projectDto, ProjectEntity.class);
    }

    @Override
    public ProjectEntity mapDtoToEntity(final ProjectDto profileDto) {
        return this.modelMapper.map(profileDto, ProjectEntity.class);
    }

    @Override
    public ProjectDto mapEntityToDto(ProjectEntity entity) {
        return this.modelMapper.map(entity, ProjectDto.class);
    }
}
