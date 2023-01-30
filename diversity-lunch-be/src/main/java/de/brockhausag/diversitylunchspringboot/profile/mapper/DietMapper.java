package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DietMapper implements DimensionMapper<DietDto, BasicDimensionSelectableOption> {

    private final BasicDimensionService dimensionService;

    @Override
    public DietDto entityToDto(BasicDimensionSelectableOption entity) {
        DietDto DietDto = new DietDto();
        DietDto.setId(entity.getId());
        DietDto.setDescriptor(entity.getValue());
        return DietDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(DietDto dto) {
        return BasicDimensionSelectableOption.builder()
                .id(dto.getId())
                .value(dto.getDescriptor())
                .ignoreInScoring(false)
                .dimensionCategory(dimensionService.getDimension("Ernährung").get().getDimensionCategory())
                .build();
    }
}