package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.SocialBackgroundDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocialBackgroundMapper implements DimensionMapper<SocialBackgroundDto, BasicDimensionSelectableOption> {

    private final BasicDimensionService dimensionService;

    @Override
    public SocialBackgroundDto entityToDto(BasicDimensionSelectableOption entity) {
        SocialBackgroundDto SocialBackgroundDto = new SocialBackgroundDto();
        SocialBackgroundDto.setId(entity.getId());
        SocialBackgroundDto.setDescriptor(entity.getValue());
        return SocialBackgroundDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(SocialBackgroundDto dto) {
        return BasicDimensionSelectableOption.builder()
                .id(dto.getId())
                .value(dto.getDescriptor())
                .ignoreInScoring(false)
                .dimensionCategory(dimensionService.getDimension("Soziale Herkunft").getDimensionCategory())
                .build();
    }
}
