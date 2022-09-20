package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.WorkExperienceDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.WorkExperienceEntity;

public class WorkExperienceTestDataFactory {
    private static final  int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"incomplete", "first workExperience", "second workExperience", "third workExperience"};

    public WorkExperienceDto buildDto(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new WorkExperienceDto(ids[setNumber], descriptors[setNumber]);
        }
        return new WorkExperienceDto(ids[1], descriptors[1]);
    }


    public WorkExperienceEntity buildEntity(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new WorkExperienceEntity(ids[setNumber], descriptors[setNumber]);
        }
        return new WorkExperienceEntity(ids[1], descriptors[1]);
    }

    public WorkExperienceEntity buildEntityWithoutId(){
        WorkExperienceEntity incompleteEntity = new WorkExperienceEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public WorkExperienceEntity buildEntityWithoutDescriptor(){
        WorkExperienceEntity incompleteEntity = new WorkExperienceEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public WorkExperienceDto buildDtoWithoutId(){
        WorkExperienceDto incompleteDto = new WorkExperienceDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public WorkExperienceDto buildDtoWithoutDescriptor(){
        WorkExperienceDto incompleteDto = new WorkExperienceDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
