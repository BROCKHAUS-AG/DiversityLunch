package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.DietEntity;

public class DietTestDataFactory {

    private static final Long firstId = 1L, secondId = 2L, thirdId =3L,
            incompleteId = 666L;
    private static final String firstDescriptor = "first diet", secondDescriptor = "second diet",
            thirdDescriptor = "third diet", incompleteDescriptor = "incomplete";

    public DietDto buildDto(int setNumber){
        if ( setNumber == 2){
            return new DietDto(secondId, secondDescriptor);
        }

        if (setNumber == 3){
            return new DietDto(thirdId, secondDescriptor);
        }
        return new DietDto(firstId, firstDescriptor);
    }


    public DietEntity buildEntity(int setNumber){
        if ( setNumber == 2){
            return new DietEntity(secondId, secondDescriptor);
        }
        if (setNumber == 3){
            return new DietEntity(thirdId, thirdDescriptor);
        }
        return new DietEntity(firstId, firstDescriptor);
    }

    public DietEntity buildEntityWithoutId(){
        DietEntity incompleteEntity = new DietEntity();
        incompleteEntity.setDescriptor(incompleteDescriptor);
        return incompleteEntity;
    }

    public DietEntity buildEntityWithoutDescriptor() {
        DietEntity incompleteEntity = new DietEntity();
        incompleteEntity.setId(incompleteId);
        return incompleteEntity;
    }

    public DietDto buildDtoWithoutId(){
        DietDto incompleteDto = new DietDto();
        incompleteDto.setDescriptor(incompleteDescriptor);
        return incompleteDto;
    }

    public DietDto buildDtoWithoutDescriptor(){
        DietDto incompleteDto = new DietDto();
        incompleteDto.setId(incompleteId);
        return incompleteDto;
    }
}
