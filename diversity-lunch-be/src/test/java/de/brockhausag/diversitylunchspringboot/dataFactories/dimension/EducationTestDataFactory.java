package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;

public class EducationTestDataFactory {

    private static final int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"incomplete", "first education", "second education", "third education"};

    public EducationDto buildDto(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new EducationDto(ids[setNumber], descriptors[setNumber], false);
        }
        return new EducationDto(ids[1], descriptors[1], false);
    }


    public EducationEntity buildEntity(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new EducationEntity(ids[setNumber], descriptors[setNumber], false);
        }
        return new EducationEntity(ids[1], descriptors[1], false);
    }

    public EducationEntity buildEntityWithoutId() {
        EducationEntity incompleteEntity = new EducationEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public EducationEntity buildEntityWithoutDescriptor() {
        EducationEntity incompleteEntity = new EducationEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public EducationDto buildDtoWithoutId() {
        EducationDto incompleteDto = new EducationDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public EducationDto buildDtoWithoutDescriptor() {
        EducationDto incompleteDto = new EducationDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
