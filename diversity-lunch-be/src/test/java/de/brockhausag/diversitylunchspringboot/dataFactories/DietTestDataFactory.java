package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.DietEntity;

public class DietTestDataFactory {

    private static final  int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"incomplete", "first diet", "second diet", "third diet"};

    public DietDto buildDto(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new DietDto(ids[setNumber], descriptors[setNumber]);
        }
        return new DietDto(ids[1], descriptors[1]);
    }


    public DietEntity buildEntity(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new DietEntity(ids[setNumber], descriptors[setNumber]);
        }
        return new DietEntity(ids[1], descriptors[1]);
    }

    public DietEntity buildEntityWithoutId(){
        DietEntity incompleteEntity = new DietEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public DietEntity buildEntityWithoutDescriptor(){
        DietEntity incompleteEntity = new DietEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public DietDto buildDtoWithoutId(){
        DietDto incompleteDto = new DietDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public DietDto buildDtoWithoutDescriptor(){
        DietDto incompleteDto = new DietDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
