package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountryMapper implements DimensionMapper<CountryDto, BasicDimensionSelectableOption> {

    private final BasicDimensionSelectableOptionRepository repository;

    @Override
    public CountryDto entityToDto(BasicDimensionSelectableOption entity) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(entity.getDimensionCategory().getId());
        countryDto.setDescriptor(entity.getDimensionCategory().getDescription());
        return countryDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(CountryDto dto) {
        return repository.getByDimensionCategory_Description(dto.getDescriptor());
    }
}
