package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

public class SexualOrientationTestDataFactory {
    private static final int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"Asexuell", "Pansexuell", "keine Angabe", "Heterosexuell"};

    public SexualOrientationDto buildDto(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new SexualOrientationDto(ids[setNumber], descriptors[setNumber]);
        }
        return new SexualOrientationDto(ids[1], descriptors[1]);
    }


    public SexualOrientationEntity buildEntity(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new SexualOrientationEntity(ids[setNumber], descriptors[setNumber], false);
        }
        return new SexualOrientationEntity(ids[1], descriptors[1], false);
    }

    public SexualOrientationEntity buildEntityWithoutId() {
        SexualOrientationEntity incompleteEntity = new SexualOrientationEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public SexualOrientationEntity buildEntityWithoutDescriptor() {
        SexualOrientationEntity incompleteEntity = new SexualOrientationEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public SexualOrientationDto buildDtoWithoutId() {
        SexualOrientationDto incompleteDto = new SexualOrientationDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public SexualOrientationDto buildDtoWithoutDescriptor() {
        SexualOrientationDto incompleteDto = new SexualOrientationDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}

