package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.WeightedDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.WorkExperienceDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkExperienceMapper implements DimensionMapper<WorkExperienceDto, WeightedDimensionSelectableOption> {

    private final WeightedDimensionService dimensionService;

    @Override
    public WorkExperienceDto entityToDto(WeightedDimensionSelectableOption entity) {
        WorkExperienceDto WorkExperienceDto = new WorkExperienceDto();
        WorkExperienceDto.setId(entity.getId());
        WorkExperienceDto.setDescriptor(entity.getValue());
        return WorkExperienceDto;
    }

    @Override
    public WeightedDimensionSelectableOption dtoToEntity(WorkExperienceDto dto) {
        return WeightedDimensionSelectableOption.builder()
                .id(dto.getId())
                .value(dto.getDescriptor())
                .ignoreInScoring(false)
                .dimensionCategory(dimensionService.getDimension("Berufserfahrung").get().getDimensionCategory())
                .build();
    }
}
