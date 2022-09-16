package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ReligionDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ReligionEntity;

public class ReligionTestDataFactory {

    private static final Long firstId = 1L, secondId = 2L, thirdId =3L,
            incompleteId = 666L;
    private static final String firstDescriptor = "first religion", secondDescriptor = "second religion",
            thirdDescriptor = "third religion", incompleteDescriptor = "incomplete";

    public ReligionDto buildDto(int setNumber){
        if ( setNumber == 2){
            return new ReligionDto(secondId, secondDescriptor);
        }

        if (setNumber == 3){
            return new ReligionDto(thirdId, secondDescriptor);
        }
        return new ReligionDto(firstId, firstDescriptor);
    }


    public ReligionEntity buildEntity(int setNumber){
        if ( setNumber == 2){
            return new ReligionEntity(secondId, secondDescriptor);
        }
        if (setNumber == 3){
            return new ReligionEntity(thirdId, thirdDescriptor);
        }
        return new ReligionEntity(firstId, firstDescriptor);
    }

    public ReligionEntity buildEntityWithoutId(){
        return new ReligionEntity(null, incompleteDescriptor);
    }

    public ReligionEntity buildEntityWithoutDescriptor(){
        return new ReligionEntity(incompleteId, null);
    }

    public ReligionDto buildDtoWithoutId(){
        return new ReligionDto(null, incompleteDescriptor);
    }

    public ReligionDto buildDtoWithoutDescriptor(){
        return new ReligionDto(incompleteId, null);
    }
}
