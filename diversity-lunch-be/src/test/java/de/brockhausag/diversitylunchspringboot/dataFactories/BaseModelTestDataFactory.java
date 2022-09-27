package de.brockhausag.diversitylunchspringboot.dataFactories;

public class BaseModelTestDataFactory {

    private static final  int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"incomplete", "first test object", "second test object", "third test object"};

    public TestBaseDto buildDto(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new TestBaseDto(ids[setNumber], descriptors[setNumber]);
        }
        return new TestBaseDto(ids[1], descriptors[1]);
    }


    public TestBaseEntity buildEntity(int setNumber){
        if ( (setNumber >= 1) && setNumber <= numberOfCompleteSets){
            return new TestBaseEntity(ids[setNumber], descriptors[setNumber]);
        }
        return new TestBaseEntity(ids[1], descriptors[1]);
    }

    public TestBaseEntity buildEntityWithoutId(){
        TestBaseEntity incompleteEntity = new TestBaseEntity();
        incompleteEntity.setDescriptor(descriptors[0]);
        return incompleteEntity;
    }

    public TestBaseEntity buildEntityWithoutDescriptor(){
        TestBaseEntity incompleteEntity = new TestBaseEntity();
        incompleteEntity.setId(ids[0]);
        return incompleteEntity;
    }

    public TestBaseDto buildDtoWithoutId(){
        TestBaseDto incompleteDto = new TestBaseDto();
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public TestBaseDto buildDtoWithoutDescriptor(){
        TestBaseDto incompleteDto = new TestBaseDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
