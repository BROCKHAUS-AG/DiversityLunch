package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EducationMapper implements DimensionMapper<EducationDto, BasicDimensionSelectableOption> {

    private final BasicDimensionService dimensionService;

    @Override
    public EducationDto entityToDto(BasicDimensionSelectableOption entity) {
        EducationDto EducationDto = new EducationDto();
        EducationDto.setId(entity.getId());
        EducationDto.setDescriptor(entity.getValue());
        return EducationDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(EducationDto dto) {
        return BasicDimensionSelectableOption.builder()
                .id(dto.getId())
                .value(dto.getDescriptor())
                .ignoreInScoring(false)
                .dimensionCategory(dimensionService.getDimension("Bildungsweg").get().getDimensionCategory())
                .build();
    }
}