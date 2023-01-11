package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountryMapper implements DimensionMapper<CountryDto, BasicDimensionSelectableOption> {

    private final BasicDimensionService dimensionService;

    @Override
    public CountryDto entityToDto(BasicDimensionSelectableOption entity) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(entity.getId());
        countryDto.setDescriptor(entity.getValue());
        return countryDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(CountryDto dto) {
        return BasicDimensionSelectableOption.builder()
                .id(dto.getId())
                .value(dto.getDescriptor())
                .ignoreInScoring(false)
                .dimensionCategory(dimensionService.getDimension("Ethnische Herkunft").getDimensionCategory())
                .build();
    }
}
