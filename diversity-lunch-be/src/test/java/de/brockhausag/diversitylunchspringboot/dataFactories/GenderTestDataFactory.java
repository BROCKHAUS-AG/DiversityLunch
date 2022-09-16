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
        return new GenderEntity(null, incompleteDescriptor);
    }

    public GenderEntity buildEntityWithoutDescriptor(){
        return new GenderEntity(incompleteId, null);
    }

    public GenderDto buildDtoWithoutId(){
        return new GenderDto(null, incompleteDescriptor);
    }

    public GenderDto buildDtoWithoutDescriptor(){
        return new GenderDto(incompleteId, null);
    }
}
