package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyCategoryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyCategoryEntity;

public class HobbyCategoryTestDataFactory {
    private static final  int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"incomplete", "first hobbyCategory", "second hobbyCategory", "third hobbyCategory"};

    public HobbyCategoryDto buildDto(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new HobbyCategoryDto(ids[setNumber], descriptors[setNumber]);
        }
        return new HobbyCategoryDto(ids[1], descriptors[1]);
    }


    public HobbyCategoryEntity buildEntity(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new HobbyCategoryEntity(ids[setNumber], descriptors[setNumber]);
        }
        return new HobbyCategoryEntity(ids[1], descriptors[1]);
    }

    public HobbyCategoryEntity buildEntityWithoutId(){
        HobbyCategoryEntity incompleteEntity = new HobbyCategoryEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public HobbyCategoryEntity buildEntityWithoutDescriptor(){
        HobbyCategoryEntity incompleteEntity = new HobbyCategoryEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public HobbyCategoryDto buildDtoWithoutId(){
        HobbyCategoryDto incompleteDto = new HobbyCategoryDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public HobbyCategoryDto buildDtoWithoutDescriptor(){
        HobbyCategoryDto incompleteDto = new HobbyCategoryDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
