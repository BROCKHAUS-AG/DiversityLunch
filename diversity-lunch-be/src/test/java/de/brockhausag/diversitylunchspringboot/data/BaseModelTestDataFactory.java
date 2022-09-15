package de.brockhausag.diversitylunchspringboot.data;

public class BaseModelTestDataFactory {

    private final Long firstId = 1L;
    private final Long secondId = 2L;
    private final Long thirdId = 3L;
    private final String firstDescriptor = "First Object";
    private final String secondDescriptor = "Second Object";
    private final String thirdDescriptor = "Third Object";
    
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
    public TestBaseDto buildTestBaseDto() {return firstDtoBuilder().build();}


    public TestBaseDto.TestBaseDtoBuilder secondDtoBuilder(){
        return TestBaseDto.builder()
                .id(secondId)
                .descriptor(secondDescriptor);
    }
    public TestBaseDto buildSecondTestBaseDto(){return secondDtoBuilder().build();}

    public TestBaseDto.TestBaseDtoBuilder thirdDtoBuilder(){
        return TestBaseDto.builder()
                .id(thirdId)
                .descriptor(thirdDescriptor);
    }
    public TestBaseDto buildThirdTestBaseDto(){return thirdDtoBuilder().build();}
}
