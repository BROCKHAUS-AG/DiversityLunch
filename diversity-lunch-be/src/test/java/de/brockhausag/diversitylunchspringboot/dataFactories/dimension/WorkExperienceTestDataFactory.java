package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

public class WorkExperienceTestDataFactory {
    private static final int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final int[] weights = {0, 1, 2, 3};


    private static final String[] descriptors = {"incomplete", "0-3 Jahre", "4-10 Jahre", "über 10 Jahre"};

    public WorkExperienceDto buildDto(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new WorkExperienceDto(ids[setNumber], descriptors[setNumber]);
        }
        return new WorkExperienceDto(ids[1], descriptors[1]);
    }


    public WorkExperienceEntity buildEntity(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new WorkExperienceEntity(ids[setNumber], descriptors[setNumber], weights[setNumber], false);
        }
        return new WorkExperienceEntity(ids[1], descriptors[1], weights[1], false);
    }

    public WorkExperienceEntity buildEntityWithoutId() {
        WorkExperienceEntity incompleteEntity = new WorkExperienceEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public WorkExperienceEntity buildEntityWithoutDescriptor() {
        WorkExperienceEntity incompleteEntity = new WorkExperienceEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public WorkExperienceDto buildDtoWithoutId() {
        WorkExperienceDto incompleteDto = new WorkExperienceDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public WorkExperienceDto buildDtoWithoutDescriptor() {
        WorkExperienceDto incompleteDto = new WorkExperienceDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
