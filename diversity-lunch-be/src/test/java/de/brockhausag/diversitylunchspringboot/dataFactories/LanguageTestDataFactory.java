package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.LanguageDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.LanguageEntity;

public class LanguageTestDataFactory {

    private static final int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"incomplete", "first language", "second language", "third language"};

    public LanguageDto buildDto(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new LanguageDto(ids[setNumber], descriptors[setNumber]);
        }
        return new LanguageDto(ids[1], descriptors[1]);
    }


    public LanguageEntity buildEntity(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new LanguageEntity(ids[setNumber], descriptors[setNumber]);
        }
        return new LanguageEntity(ids[1], descriptors[1]);
    }

    public LanguageEntity buildEntityWithoutId() {
        LanguageEntity incompleteEntity = new LanguageEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public LanguageEntity buildEntityWithoutDescriptor() {
        LanguageEntity incompleteEntity = new LanguageEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public LanguageDto buildDtoWithoutId() {
        LanguageDto incompleteDto = new LanguageDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public LanguageDto buildDtoWithoutDescriptor() {
        LanguageDto incompleteDto = new LanguageDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
