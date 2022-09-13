package de.brockhausag.diversitylunchspringboot.data;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.CountryEntity;

public class CountryTestDataFactory {

    private final Long id = 7L;
    private final String descriptor = "Johto";
    private final Long secondId = 9L;
    private final String secondDescriptor = "Kanto";

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
    
    public CountryEntity.CountryEntityBuilder secondEntityBuilder(){
        return CountryEntity.builder()
                .id(secondId)
                .descriptor(secondDescriptor);
    }
    public CountryEntity buildSecondCountryEntity(){return secondEntityBuilder().build();}

    public CountryDto.CountryDtoBuilder secondDtoBuilder(){
        return CountryDto.builder()
                .id(secondId)
                .descriptor(secondDescriptor);
    }
    public CountryDto buildSecondCountryDto(){return secondDtoBuilder().build();}
}
