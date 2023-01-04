package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.ProjectDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectMapper implements DimensionMapper<ProjectDto, BasicDimensionSelectableOption> {

    private final BasicDimensionSelectableOptionRepository repository;

    @Override
    public ProjectDto entityToDto(BasicDimensionSelectableOption entity) {
        ProjectDto ProjectDto = new ProjectDto();
        ProjectDto.setId(entity.getDimensionCategory().getId());
        ProjectDto.setDescriptor(entity.getDimensionCategory().getDescription());
        return ProjectDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(ProjectDto dto) {
        return repository.findByDimensionCategoryByDescription(dto.getDescriptor());
    }
}
