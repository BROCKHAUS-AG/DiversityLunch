package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProjectDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProjectEntity;

public class ProjectTestDataFactory {

    private static final Long firstId = 1L, secondId = 2L, thirdId =3L,
            incompleteId = 666L;
    private static final String firstDescriptor = "first project", secondDescriptor = "second project",
            thirdDescriptor = "third project", incompleteDescriptor = "incomplete";

    public ProjectDto buildDto(int setNumber){
        if ( setNumber == 2){
            return new ProjectDto(secondId, secondDescriptor);
        }

        if (setNumber == 3){
            return new ProjectDto(thirdId, secondDescriptor);
        }
        return new ProjectDto(firstId, firstDescriptor);
    }


    public ProjectEntity buildEntity(int setNumber){
        if ( setNumber == 2){
            return new ProjectEntity(secondId, secondDescriptor);
        }
        if (setNumber == 3){
            return new ProjectEntity(thirdId, thirdDescriptor);
        }
        return new ProjectEntity(firstId, firstDescriptor);
    }

    public ProjectEntity buildEntityWithoutId(){
        ProjectEntity incompleteEntity = new ProjectEntity();
        incompleteEntity.setDescriptor(incompleteDescriptor);
        return incompleteEntity;
    }

    public ProjectEntity buildEntityWithoutDescriptor(){
        ProjectEntity incompleteEntity = new ProjectEntity();
        incompleteEntity.setId(incompleteId);
        return incompleteEntity;
    }

    public ProjectDto buildDtoWithoutId(){
        ProjectDto incompleteDto = new ProjectDto();
        incompleteDto.setDescriptor(incompleteDescriptor);
        return incompleteDto;
    }

    public ProjectDto buildDtoWithoutDescriptor(){
        ProjectDto incompleteDto = new ProjectDto();
        incompleteDto.setId(incompleteId);
        return incompleteDto;
    }
}
