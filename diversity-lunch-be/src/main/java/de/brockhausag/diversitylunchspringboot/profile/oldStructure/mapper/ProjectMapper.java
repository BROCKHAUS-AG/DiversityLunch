package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.ProjectDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectMapper implements DimensionMapper<ProjectDto, BasicDimensionSelectableOption> {

    private final BasicDimensionService dimensionService;

    @Override
    public ProjectDto entityToDto(BasicDimensionSelectableOption entity) {
        ProjectDto ProjectDto = new ProjectDto();
        ProjectDto.setId(entity.getId());
        ProjectDto.setDescriptor(entity.getValue());
        return ProjectDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(ProjectDto dto) {
        return BasicDimensionSelectableOption.builder()
                .id(dto.getId())
                .value(dto.getDescriptor())
                .ignoreInScoring(false)
                .dimensionCategory(dimensionService.getDimension("Projekt").get().getDimensionCategory())
                .build();
    }
}
