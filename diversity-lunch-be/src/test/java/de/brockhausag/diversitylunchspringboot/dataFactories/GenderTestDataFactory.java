package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.GenderDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.GenderEntity;

public class GenderTestDataFactory {

    private static final Long firstId = 1L, secondId = 2L, thirdId =3L,
            incompleteId = 666L;
    private static final String firstDescriptor = "first gender", secondDescriptor = "second gender",
            thirdDescriptor = "third gender", incompleteDescriptor = "incomplete";

    public GenderDto buildDto(int setNumber){
        if ( setNumber == 2){
            return new GenderDto(secondId, secondDescriptor);
        }

        if (setNumber == 3){
            return new GenderDto(thirdId, secondDescriptor);
        }
        return new GenderDto(firstId, firstDescriptor);
    }


    public GenderEntity buildEntity(int setNumber){
        if ( setNumber == 2){
            return new GenderEntity(secondId, secondDescriptor);
        }
        if (setNumber == 3){
            return new GenderEntity(thirdId, thirdDescriptor);
        }
        return new GenderEntity(firstId, firstDescriptor);
    }

    public GenderEntity buildEntityWithoutId(){
        GenderEntity incompleteEntity = new GenderEntity();
        incompleteEntity.setDescriptor(incompleteDescriptor);
        return incompleteEntity;
    }

    public GenderEntity buildEntityWithoutDescriptor(){
        GenderEntity incompleteEntity = new GenderEntity();
        incompleteEntity.setId(incompleteId);
        return incompleteEntity;
    }

    public GenderDto buildDtoWithoutId(){
        GenderDto incompleteDto = new GenderDto();
        incompleteDto.setDescriptor(incompleteDescriptor);
        return incompleteDto;
    }

    public GenderDto buildDtoWithoutDescriptor(){
        GenderDto incompleteDto = new GenderDto();
        incompleteDto.setId(incompleteId);
        return incompleteDto;
    }
}
