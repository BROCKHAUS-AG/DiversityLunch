package de.brockhausag.diversitylunchspringboot.dataFactories;

public class BaseModelTestDataFactory {

    private static final Long firstId = 1L, secondId = 2L, thirdId = 3L;
    private static final String firstDescriptor = "First Object", secondDescriptor = "Second Object",
            thirdDescriptor = "Third Object";
    
    public TestBaseEntity.TestBaseEntityBuilder firstEntityBuilder() {
        return TestBaseEntity.builder()
                .id(firstId)
                .descriptor(firstDescriptor);
    }
    public TestBaseEntity buildFirstEntity() {return firstEntityBuilder().build();}

    public TestBaseEntity.TestBaseEntityBuilder secondEntityBuilder(){
        return TestBaseEntity.builder()
                .id(secondId)
                .descriptor(secondDescriptor);
    }
    public TestBaseEntity buildSecondEntity(){return secondEntityBuilder().build();}

    public TestBaseEntity.TestBaseEntityBuilder thirdEntityBuilder(){
        return TestBaseEntity.builder()
                .id(thirdId)
                .descriptor(thirdDescriptor);
    }
    public TestBaseEntity buildThirdEntity(){return thirdEntityBuilder().build();}


    public TestBaseDto.TestBaseDtoBuilder firstDtoBuilder() {
        return TestBaseDto.builder()
                .id(firstId)
                .descriptor(firstDescriptor);
    }
    public TestBaseDto buildFirstDto() {return firstDtoBuilder().build();}


    public TestBaseDto.TestBaseDtoBuilder secondDtoBuilder(){
        return TestBaseDto.builder()
                .id(secondId)
                .descriptor(secondDescriptor);
    }
    public TestBaseDto buildSecondDto(){return secondDtoBuilder().build();}

    public TestBaseDto.TestBaseDtoBuilder thirdDtoBuilder(){
        return TestBaseDto.builder()
                .id(thirdId)
                .descriptor(thirdDescriptor);
    }
    public TestBaseDto buildThirdDto(){return thirdDtoBuilder().build();}
}
