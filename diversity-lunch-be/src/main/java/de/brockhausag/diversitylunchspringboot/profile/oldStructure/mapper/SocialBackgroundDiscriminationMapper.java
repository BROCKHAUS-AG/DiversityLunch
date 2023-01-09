package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.SocialBackgroundDiscriminationDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocialBackgroundDiscriminationMapper implements DimensionMapper<SocialBackgroundDiscriminationDto, BasicDimensionSelectableOption> {

    private final BasicDimensionService dimensionService;

    @Override
    public SocialBackgroundDiscriminationDto entityToDto(BasicDimensionSelectableOption entity) {
        SocialBackgroundDiscriminationDto SocialBackgroundDiscriminationDto = new SocialBackgroundDiscriminationDto();
        SocialBackgroundDiscriminationDto.setId(entity.getId());
        SocialBackgroundDiscriminationDto.setDescriptor(entity.getValue());
        return SocialBackgroundDiscriminationDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(SocialBackgroundDiscriminationDto dto) {
        return BasicDimensionSelectableOption.builder()
                .id(dto.getId())
                .value(dto.getDescriptor())
                .ignoreInScoring(false)
                .dimensionCategory(dimensionService.getDimension("Diskriminierung aufgrund sozialer Herkunft").getDimensionCategory())
                .build();
    }
}
