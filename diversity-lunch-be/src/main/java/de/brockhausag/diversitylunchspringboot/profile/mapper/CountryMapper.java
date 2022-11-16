package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.CountryEntity;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryMapper implements Mapper<CountryDto, CountryEntity> {

    @Override
    public CountryDto entityToDto(CountryEntity entity) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(entity.getId());
        countryDto.setDescriptor(entity.getDescriptor());
        return countryDto;
    }

    @Override
    public CountryEntity dtoToEntity(CountryDto dto) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(dto.getId());
        countryEntity.setDescriptor(dto.getDescriptor());
        return countryEntity;
    }

    @Override
    public List<CountryDto> entityToDto(List<CountryEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<CountryEntity> dtoToEntity(List<CountryDto> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
