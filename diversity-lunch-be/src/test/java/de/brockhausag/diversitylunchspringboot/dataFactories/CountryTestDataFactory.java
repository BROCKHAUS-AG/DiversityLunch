package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.CountryEntity;

public class CountryTestDataFactory {

    private static final Long firstId = 1L, secondId = 2L, thirdId =3L,
            incompleteId = 666L;
    private static final String firstDescriptor = "first country", secondDescriptor = "second country",
            thirdDescriptor = "third country", incompleteDescriptor = "incomplete";

    public CountryDto buildDto(int setNumber){
        if ( setNumber == 2){
            return new CountryDto(secondId, secondDescriptor);
        }

        if (setNumber == 3){
            return new CountryDto(thirdId, secondDescriptor);
        }
        return new CountryDto(firstId, firstDescriptor);
    }


    public CountryEntity buildEntity(int setNumber){
        if ( setNumber == 2){
            return new CountryEntity(secondId, secondDescriptor);
        }
        if (setNumber == 3){
            return new CountryEntity(thirdId, thirdDescriptor);
        }
        return new CountryEntity(firstId, firstDescriptor);
    }

    public CountryEntity buildEntityWithoutId(){
        return new CountryEntity(null, incompleteDescriptor);
    }

    public CountryEntity buildEntityWithoutDescriptor(){
        return new CountryEntity(incompleteId, null);
    }

    public CountryDto buildDtoWithoutId(){
        return new CountryDto(null, incompleteDescriptor);
    }

    public CountryDto buildDtoWithoutDescriptor(){
        return new CountryDto(incompleteId, null);
    }
}
