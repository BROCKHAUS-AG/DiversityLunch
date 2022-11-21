package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SocialBackgroundDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SocialBackgroundEntity;

public class SocialBackgroundTestDataFactory {
    private static final  int numberOfCompleteSets = 2;
    private static final Long[] ids = {666L, 1L, 2L};
    private static final String[] descriptors = {"Akademikerfamilie", "Nichtakademisches Elternhaus", "Keine Angabe"};


    public SocialBackgroundDto buildDto(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new SocialBackgroundDto(ids[setNumber], descriptors[setNumber]);
        }
        return new SocialBackgroundDto(ids[1], descriptors[1]);
    }


    public SocialBackgroundEntity buildEntity(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new SocialBackgroundEntity(ids[setNumber], descriptors[setNumber]);
        }
        return new SocialBackgroundEntity(ids[1], descriptors[1]);
    }

    public SocialBackgroundEntity buildEntityWithoutId(){
        SocialBackgroundEntity incompleteEntity = new SocialBackgroundEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public SocialBackgroundEntity buildEntityWithoutDescriptor(){
        SocialBackgroundEntity incompleteEntity = new SocialBackgroundEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public SocialBackgroundDto buildDtoWithoutId(){
        SocialBackgroundDto incompleteDto = new SocialBackgroundDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public SocialBackgroundDto buildDtoWithoutDescriptor(){
        SocialBackgroundDto incompleteDto = new SocialBackgroundDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
