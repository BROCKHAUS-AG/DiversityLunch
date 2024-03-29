package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.MultiselectDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HobbyMapper implements DimensionMapper<HobbyDto, MultiselectDimensionSelectableOption> {

    private final MultiselectDimensionService dimensionService;

    @Override
    public HobbyDto entityToDto(MultiselectDimensionSelectableOption entity) {
        HobbyDto HobbyDto = new HobbyDto();
        HobbyDto.setId(entity.getId());
        HobbyDto.setDescriptor(entity.getValue());
        return HobbyDto;
    }

    @Override
    public MultiselectDimensionSelectableOption dtoToEntity(HobbyDto dto) {
        return MultiselectDimensionSelectableOption.builder()
                .id(dto.getId())
                .value(dto.getDescriptor())
                .dimensionCategory(dimensionService.getDimension("Hobby").get().getDimensionCategory())
                .build();
    }
}