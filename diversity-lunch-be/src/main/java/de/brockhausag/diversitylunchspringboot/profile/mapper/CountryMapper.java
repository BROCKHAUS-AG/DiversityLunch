package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.CountryEntity;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionMapper;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper implements DimensionMapper<CountryDto, CountryEntity> {

    @Override
    public CountryDto entityToDto(CountryEntity entity) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(entity.getId());
        countryDto.setDescriptor(entity.getDescriptor());
        countryDto.setDefault(entity.isDefault());
        return countryDto;
    }

    @Override
    public CountryEntity dtoToEntity(CountryDto dto) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(dto.getId());
        countryEntity.setDescriptor(dto.getDescriptor());
        countryEntity.setDefault(dto.isDefault());
        return countryEntity;
    }
}
