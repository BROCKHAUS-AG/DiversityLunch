package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.TestBasicDimension;

public class BaseModelTestDataFactory {

    private static final int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"incomplete", "first test object", "second test object", "third test object"};

    public TestDefaultDimensionDto buildDto(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new TestDefaultDimensionDto(ids[setNumber], descriptors[setNumber]);
        }
        return new TestDefaultDimensionDto(ids[1], descriptors[1]);
    }


    public TestBasicDimension buildEntity(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new TestBasicDimension(ids[setNumber], descriptors[setNumber]);
        }
        return new TestBasicDimension(ids[1], descriptors[1]);
    }

    public TestBasicDimension buildEntityWithoutId() {
        TestBasicDimension incompleteEntity = new TestBasicDimension();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public TestBasicDimension buildEntityWithoutDescriptor() {
        TestBasicDimension incompleteEntity = new TestBasicDimension();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public TestDefaultDimensionDto buildDtoWithoutId() {
        TestDefaultDimensionDto incompleteDto = new TestDefaultDimensionDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public TestDefaultDimensionDto buildDtoWithoutDescriptor() {
        TestDefaultDimensionDto incompleteDto = new TestDefaultDimensionDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
