package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SocialBackgroundDiscriminationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SocialBackgroundDiscriminationEntity;

public class SocialBackgroundDiscriminationTestDataFactory {
    private static final  int numberOfCompleteSets = 2;
    private static final Long[] ids = {666L, 1L, 2L};
    private static final String[] descriptors = {"Akademikerfamilie", "Nichtakademisches Elternhaus", "Keine Angabe"};


    public SocialBackgroundDiscriminationDto buildDto(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new SocialBackgroundDiscriminationDto(ids[setNumber], descriptors[setNumber]);
        }
        return new SocialBackgroundDiscriminationDto(ids[1], descriptors[1]);
    }


    public SocialBackgroundDiscriminationEntity buildEntity(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new SocialBackgroundDiscriminationEntity(ids[setNumber], descriptors[setNumber]);
        }
        return new SocialBackgroundDiscriminationEntity(ids[1], descriptors[1]);
    }

    public SocialBackgroundDiscriminationEntity buildEntityWithoutId(){
        SocialBackgroundDiscriminationEntity incompleteEntity = new SocialBackgroundDiscriminationEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public SocialBackgroundDiscriminationEntity buildEntityWithoutDescriptor(){
        SocialBackgroundDiscriminationEntity incompleteEntity = new SocialBackgroundDiscriminationEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public SocialBackgroundDiscriminationDto buildDtoWithoutId(){
        SocialBackgroundDiscriminationDto incompleteDto = new SocialBackgroundDiscriminationDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public SocialBackgroundDiscriminationDto buildDtoWithoutDescriptor(){
        SocialBackgroundDiscriminationDto incompleteDto = new SocialBackgroundDiscriminationDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
