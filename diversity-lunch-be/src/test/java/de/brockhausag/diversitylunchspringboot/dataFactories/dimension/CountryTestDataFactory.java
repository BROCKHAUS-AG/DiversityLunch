package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.CountryEntity;

public class CountryTestDataFactory {

    private static final int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"incomplete", "first country", "second country", "third country"};

    public CountryDto buildDto(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new CountryDto(ids[setNumber], descriptors[setNumber], false);
        }
        return new CountryDto(ids[1], descriptors[1], false);
    }


    public CountryEntity buildEntity(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new CountryEntity(ids[setNumber], descriptors[setNumber], false);
        }
        return new CountryEntity(ids[1], descriptors[1], false);
    }

    public CountryEntity buildEntityWithoutId() {
        CountryEntity incompleteEntity = new CountryEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public CountryEntity buildEntityWithoutDescriptor() {
        CountryEntity incompleteEntity = new CountryEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public CountryDto buildDtoWithoutId() {
        CountryDto incompleteDto = new CountryDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public CountryDto buildDtoWithoutDescriptor() {
        CountryDto incompleteDto = new CountryDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
