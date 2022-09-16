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
        return new ProjectEntity(null, incompleteDescriptor);
    }

    public ProjectEntity buildEntityWithoutDescriptor(){
        return new ProjectEntity(incompleteId, null);
    }

    public ProjectDto buildDtoWithoutId(){
        return new ProjectDto(null, incompleteDescriptor);
    }

    public ProjectDto buildDtoWithoutDescriptor(){
        return new ProjectDto(incompleteId, null);
    }
}
