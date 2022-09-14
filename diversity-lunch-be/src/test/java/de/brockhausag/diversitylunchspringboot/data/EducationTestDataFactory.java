package de.brockhausag.diversitylunchspringboot.data;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;

public class EducationTestDataFactory {

    private final Long firstId = 1L;
    private final String firstDescriptor = "firstDescriptor";
    private final Long secondId = 2L;
    private final String secondDescriptor = "secondDescriptor";

    public EducationEntity.EducationEntityBuilder entityBuilder() {
        return EducationEntity.builder()
                .id(firstId)
                .descriptor(firstDescriptor);
    }
    public EducationEntity buildEducationEntity() {
        return entityBuilder().build();
    }

    public EducationDto.EducationDtoBuilder dtoBuilder() {
        return EducationDto.builder()
                .id(firstId)
                .descriptor(firstDescriptor);
    }
    public EducationDto buildEducationDto() {
        return dtoBuilder().build();
    }

    public EducationEntity.EducationEntityBuilder secondEntityBuilder(){
        return EducationEntity.builder()
                .id(secondId)
                .descriptor(secondDescriptor);
    }
    public EducationEntity buildSecondEducationEntity(){return secondEntityBuilder().build();}

    public EducationDto.EducationDtoBuilder secondDtoBuilder(){
        return EducationDto.builder()
                .id(secondId)
                .descriptor(secondDescriptor);
    }
    public EducationDto buildSecondEducationDto(){return secondDtoBuilder().build();}
}
