package de.brockhausag.diversitylunchspringboot.data.factoriesForMapperTests;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.DietEntity;

public class DietTestDataFactory {

    private final Long id = 7L;
    private final String descriptor = "vegan";
    private final Long secondId = 9L;
    private final String secondDescriptor = "starve";

    public DietEntity.DietEntityBuilder entityBuilder() {
        return DietEntity.builder()
                .id(id)
                .descriptor(descriptor);
    }
    public DietEntity buildDietEntity() {
        return entityBuilder().build();
    }

    public DietDto.DietDtoBuilder dtoBuilder() {
        return DietDto.builder()
                .id(id)
                .descriptor(descriptor);
    }
    public DietDto buildDietDto() {
        return dtoBuilder().build();
    }

    public DietEntity.DietEntityBuilder secondEntityBuilder(){
        return DietEntity.builder()
                .id(secondId)
                .descriptor(secondDescriptor);
    }
    public DietEntity buildSecondDietEntity(){return secondEntityBuilder().build();}

    public DietDto.DietDtoBuilder secondDtoBuilder(){
        return DietDto.builder()
                .id(secondId)
                .descriptor(secondDescriptor);
    }
    public DietDto buildSecondDietDto(){return secondDtoBuilder().build();}
}
