package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SexualOrientationDto;
import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SexualOrientationMapper implements DimensionMapper<SexualOrientationDto, BasicDimensionSelectableOption> {

    private final BasicDimensionService dimensionService;

    @Override
    public SexualOrientationDto entityToDto(BasicDimensionSelectableOption entity) {
        SexualOrientationDto SexualOrientationDto = new SexualOrientationDto();
        SexualOrientationDto.setId(entity.getId());
        SexualOrientationDto.setDescriptor(entity.getValue());
        return SexualOrientationDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(SexualOrientationDto dto) {
        return BasicDimensionSelectableOption.builder()
                .id(dto.getId())
                .value(dto.getDescriptor())
                .ignoreInScoring(false)
                .dimensionCategory(dimensionService.getDimension("Sexuelle Orientierung").get().getDimensionCategory())
                .build();
    }
}
