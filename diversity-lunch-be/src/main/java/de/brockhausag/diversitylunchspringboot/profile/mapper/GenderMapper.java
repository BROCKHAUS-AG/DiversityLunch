package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.GenderDto;
import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenderMapper implements DimensionMapper<GenderDto, BasicDimensionSelectableOption> {

    private final BasicDimensionService dimensionService;

    @Override
    public GenderDto entityToDto(BasicDimensionSelectableOption entity) {
        GenderDto GenderDto = new GenderDto();
        GenderDto.setId(entity.getId());
        GenderDto.setDescriptor(entity.getValue());
        return GenderDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(GenderDto dto) {
        return BasicDimensionSelectableOption.builder()
                .id(dto.getId())
                .value(dto.getDescriptor())
                .ignoreInScoring(false)
                .dimensionCategory(dimensionService.getDimension("Geschlechtliche Identität").get().getDimensionCategory())
                .build();
    }
}