package de.brockhausag.diversitylunchspringboot.data;

import de.brockhausag.diversitylunchspringboot.account.model.AccountDto;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.CountryEntity;

public class CountryTestDataFactory {

    private Long id = 7L;
    private String descriptor = "Mordor";

    public CountryEntity.CountryEntityBuilder entityBuilder() {
        return CountryEntity.builder()
                .id(id)
                .descriptor(descriptor);
    }
    public CountryEntity buildCountryEntity() {
        return entityBuilder().build();
    }

    public CountryDto.CountryDtoBuilder dtoBuilder() {
        return CountryDto.builder()
                .id(id)
                .descriptor(descriptor);
    }
    public CountryDto buildCountryDto() {
        return dtoBuilder().build();
    }
}
