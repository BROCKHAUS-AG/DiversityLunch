package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.profile.mapper.EducationMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EducationMapperTest {

    private EducationEntity firstEntity;
    private EducationEntity secondEntity;
    private EducationEntity thirdEntity;

    private EducationDto firstDto;
    private EducationDto secondDto;
    private EducationDto thirdDto;


    private EducationMapper educationMapper;

    @BeforeEach
    void setup() {
        final Long firstId = 1L, secondId = 2L, thirdId = 3L;
        final String firstDescriptor = "First Object", secondDescriptor = "Second Object",
                thirdDescriptor = "Third Object";

        firstEntity = new EducationEntity(firstId, firstDescriptor);
        secondEntity = new EducationEntity(secondId, secondDescriptor);
        thirdEntity = new EducationEntity(thirdId, thirdDescriptor);

        firstDto = new EducationDto(firstId, firstDescriptor);
        secondDto = new EducationDto(secondId, secondDescriptor);
        thirdDto = new EducationDto(thirdId, thirdDescriptor);

        educationMapper = new EducationMapper();
    }

    @Test
    void testEntityToDto_withOneEntity_returnsOneDto() {
        //Arrange
        EducationEntity inputEntity = firstEntity;
        EducationDto expectedDto = firstDto;

        //Act
        EducationDto actualDto = educationMapper.entityToDto(inputEntity);

        //Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos() {
        //Arrange
        List<EducationEntity> inputEntityList = Arrays.asList(firstEntity, secondEntity, thirdEntity);
        List<EducationDto> expectedDtoList = Arrays.asList(firstDto, secondDto, thirdDto);

        //Act
        List<EducationDto> actualDtoList = educationMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList() {
        //Arrange
        List<EducationDto> expectedDtoList = Collections.emptyList();
        List<EducationEntity> inputEntityList = Collections.emptyList();

        //Act
        List<EducationDto> actualDtoList = educationMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity() {
        //Arrange
        EducationDto inputDto = firstDto;
        EducationEntity expectedEntity = firstEntity;


        //Act
        EducationEntity actualEntity = educationMapper.dtoToEntity(inputDto);

        //Assert
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void testDtoToEntity_withListOfTwoDtos_returnsListOfTwoEntities() {
        //Arrange
        List<EducationEntity> expectedEntityList = Arrays.asList(firstEntity, secondEntity, thirdEntity);
        List<EducationDto> inputDtoList = Arrays.asList(firstDto, secondDto, thirdDto);

        //Act
        List<EducationEntity> actualEntityList = educationMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }

    @Test
    void testDtoToEntity_withEmptyDtoList_returnsEmptyEntityList() {
        //Arrange
        List<EducationDto> inputDtoList = Collections.emptyList();
        List<EducationEntity> expectedEntityList = Collections.emptyList();

        //Act
        List<EducationEntity> actualEntityList = educationMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }


}
