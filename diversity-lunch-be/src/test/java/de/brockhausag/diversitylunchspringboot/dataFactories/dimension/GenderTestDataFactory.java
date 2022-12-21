package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.GenderDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.GenderEntity;

public class GenderTestDataFactory {

    private static final int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"incomplete", "first gender", "second gender", "third gender"};

    public GenderDto buildDto(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new GenderDto(ids[setNumber], descriptors[setNumber]);
        }
        return new GenderDto(ids[1], descriptors[1]);
    }


    public GenderEntity buildEntity(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new GenderEntity(ids[setNumber], descriptors[setNumber], false);
        }
        return new GenderEntity(ids[1], descriptors[1], false);
    }

    public GenderEntity buildEntityWithoutId() {
        GenderEntity incompleteEntity = new GenderEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public GenderEntity buildEntityWithoutDescriptor() {
        GenderEntity incompleteEntity = new GenderEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public GenderDto buildDtoWithoutId() {
        GenderDto incompleteDto = new GenderDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public GenderDto buildDtoWithoutDescriptor() {
        GenderDto incompleteDto = new GenderDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
