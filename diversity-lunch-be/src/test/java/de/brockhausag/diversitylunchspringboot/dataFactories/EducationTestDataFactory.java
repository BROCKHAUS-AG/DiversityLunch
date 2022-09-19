package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;

public class EducationTestDataFactory {

    private static final Long firstId = 1L, secondId = 2L, thirdId =3L,
                            incompleteId = 666L;
    private static final String firstDescriptor = "first education", secondDescriptor = "second education",
            thirdDescriptor = "third education", incompleteDescriptor = "incomplete";

    public EducationDto buildDto(int setNumber){
        if ( setNumber == 2){
            return new EducationDto(secondId, secondDescriptor);
        }

        if (setNumber == 3){
            return new EducationDto(thirdId, secondDescriptor);
        }
        return new EducationDto(firstId, firstDescriptor);
    }


    public EducationEntity buildEntity(int setNumber){
        if ( setNumber == 2){
            return new EducationEntity(secondId, secondDescriptor);
        }
        if (setNumber == 3){
            return new EducationEntity(thirdId, thirdDescriptor);
        }
        return new EducationEntity(firstId, firstDescriptor);
    }

    public EducationEntity buildEntityWithoutId(){
        EducationEntity incompleteEntity = new EducationEntity();
        incompleteEntity.setDescriptor(incompleteDescriptor);
        return incompleteEntity;
    }

    public EducationEntity buildEntityWithoutDescriptor(){
        EducationEntity incompleteEntity = new EducationEntity();
        incompleteEntity.setId(incompleteId);
        return incompleteEntity;
    }

    public EducationDto buildDtoWithoutId(){
        EducationDto incompleteDto = new EducationDto();
        incompleteDto.setDescriptor(incompleteDescriptor);
        return incompleteDto;
    }

    public EducationDto buildDtoWithoutDescriptor(){
        EducationDto incompleteDto = new EducationDto();
        incompleteDto.setId(incompleteId);
        return incompleteDto;
    }
}
