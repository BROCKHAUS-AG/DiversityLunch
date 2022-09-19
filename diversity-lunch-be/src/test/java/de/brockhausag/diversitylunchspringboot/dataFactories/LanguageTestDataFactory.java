package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.LanguageDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.LanguageEntity;

public class LanguageTestDataFactory {

    private static final Long firstId = 1L, secondId = 2L, thirdId =3L,
            incompleteId = 666L;
    private static final String firstDescriptor = "first language", secondDescriptor = "second language",
            thirdDescriptor = "third language", incompleteDescriptor = "incomplete";

    public LanguageDto buildDto(int setNumber){
        if ( setNumber == 2){
            return new LanguageDto(secondId, secondDescriptor);
        }

        if (setNumber == 3){
            return new LanguageDto(thirdId, secondDescriptor);
        }
        return new LanguageDto(firstId, firstDescriptor);
    }


    public LanguageEntity buildEntity(int setNumber){
        if ( setNumber == 2){
            return new LanguageEntity(secondId, secondDescriptor);
        }
        if (setNumber == 3){
            return new LanguageEntity(thirdId, thirdDescriptor);
        }
        return new LanguageEntity(firstId, firstDescriptor);
    }

    public LanguageEntity buildEntityWithoutId(){
        LanguageEntity incompleteEntity = new LanguageEntity();
        incompleteEntity.setDescriptor(incompleteDescriptor);
        return incompleteEntity;
    }

    public LanguageEntity buildEntityWithoutDescriptor(){
        LanguageEntity incompleteEntity = new LanguageEntity();
        incompleteEntity.setId(incompleteId);
        return incompleteEntity;
    }

    public LanguageDto buildDtoWithoutId(){
        LanguageDto incompleteDto = new LanguageDto();
        incompleteDto.setDescriptor(incompleteDescriptor);
        return incompleteDto;
    }

    public LanguageDto buildDtoWithoutDescriptor(){
        LanguageDto incompleteDto = new LanguageDto();
        incompleteDto.setId(incompleteId);
        return incompleteDto;
    }
}
