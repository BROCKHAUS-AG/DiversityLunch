package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProjectDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProjectEntity;

public class ProjectTestDataFactory {

    private static final  int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"incomplete", "first project", "second project", "third project"};

    public ProjectDto buildDto(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new ProjectDto(ids[setNumber], descriptors[setNumber]);
        }
        return new ProjectDto(ids[1], descriptors[1]);
    }


    public ProjectEntity buildEntity(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new ProjectEntity(ids[setNumber], descriptors[setNumber]);
        }
        return new ProjectEntity(ids[1], descriptors[1]);
    }

    public ProjectEntity buildEntityWithoutId(){
        ProjectEntity incompleteEntity = new ProjectEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public ProjectEntity buildEntityWithoutDescriptor(){
        ProjectEntity incompleteEntity = new ProjectEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public ProjectDto buildDtoWithoutId(){
        ProjectDto incompleteDto = new ProjectDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public ProjectDto buildDtoWithoutDescriptor(){
        ProjectDto incompleteDto = new ProjectDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
