package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ReligionDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ReligionEntity;

public class ReligionTestDataFactory {

    private static final int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"incomplete", "first religion", "second religion", "third religion"};

    public ReligionDto buildDto(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new ReligionDto(ids[setNumber], descriptors[setNumber]);
        }
        return new ReligionDto(ids[1], descriptors[1]);
    }


    public ReligionEntity buildEntity(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new ReligionEntity(ids[setNumber], descriptors[setNumber]);
        }
        return new ReligionEntity(ids[1], descriptors[1]);
    }

    public ReligionEntity buildEntityWithoutId() {
        ReligionEntity incompleteEntity = new ReligionEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public ReligionEntity buildEntityWithoutDescriptor() {
        ReligionEntity incompleteEntity = new ReligionEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public ReligionDto buildDtoWithoutId() {
        ReligionDto incompleteDto = new ReligionDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public ReligionDto buildDtoWithoutDescriptor() {
        ReligionDto incompleteDto = new ReligionDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
