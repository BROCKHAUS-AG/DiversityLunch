package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.profile.mapper.SexualOrientationMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SexualOrientationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualOrientationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SexualOrientationMapperTest {

    private SexualOrientationEntity firstEntity;
    private SexualOrientationEntity secondEntity;
    private SexualOrientationEntity thirdEntity;

    private SexualOrientationDto firstDto;
    private SexualOrientationDto secondDto;
    private SexualOrientationDto thirdDto;


    private SexualOrientationMapper sexualOrientationMapper;

    @BeforeEach
    void setup(){
        final Long firstId = 1L, secondId = 2L, thirdId = 3L;
        final String firstDescriptor = "First Object", secondDescriptor = "Second Object",
                thirdDescriptor = "Third Object";

        firstEntity = new SexualOrientationEntity(firstId, firstDescriptor);
        secondEntity = new SexualOrientationEntity(secondId, secondDescriptor);
        thirdEntity = new SexualOrientationEntity(thirdId, thirdDescriptor);

        firstDto = new SexualOrientationDto(firstId, firstDescriptor);
        secondDto = new SexualOrientationDto(secondId, secondDescriptor);
        thirdDto = new SexualOrientationDto(thirdId, thirdDescriptor);

        sexualOrientationMapper = new SexualOrientationMapper();
    }

    @Test
    void testEntityToDto_withOneEntity_returnsOneDto(){
        //Arrange
        SexualOrientationEntity inputEntity = firstEntity;
        SexualOrientationDto expectedDto = firstDto;

        //Act
        SexualOrientationDto actualDto = sexualOrientationMapper.entityToDto(inputEntity);

        //Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos(){
        //Arrange
        List<SexualOrientationEntity> inputEntityList = Arrays.asList(firstEntity, secondEntity, thirdEntity);
        List<SexualOrientationDto> expectedDtoList = Arrays.asList(firstDto, secondDto, thirdDto);

        //Act
        List<SexualOrientationDto> actualDtoList = sexualOrientationMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }


    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList(){
        //Arrange
        List<SexualOrientationDto> expectedDtoList = Collections.emptyList();
        List<SexualOrientationEntity> inputEntityList = Collections.emptyList();

        //Act
        List<SexualOrientationDto> actualDtoList = sexualOrientationMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity(){
        //Arrange
        SexualOrientationDto inputDto = firstDto;
        SexualOrientationEntity expectedEntity = firstEntity;


        //Act
        SexualOrientationEntity actualEntity = sexualOrientationMapper.dtoToEntity(inputDto);

        //Assert
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void testDtoToEntity_withListOfTwoDtos_returnsListOfTwoEntities(){
        //Arrange
        List<SexualOrientationEntity> expectedEntityList = Arrays.asList(firstEntity, secondEntity, thirdEntity);
        List<SexualOrientationDto> inputDtoList = Arrays.asList(firstDto, secondDto, thirdDto);

        //Act
        List<SexualOrientationEntity> actualEntityList = sexualOrientationMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }

    @Test
    void testDtoToEntity_withEmptyDtoList_returnsEmptyEntityList(){
        //Arrange
        List<SexualOrientationDto> inputDtoList = Collections.emptyList();
        List<SexualOrientationEntity> expectedEntityList = Collections.emptyList();

        //Act
        List<SexualOrientationEntity> actualEntityList = sexualOrientationMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }

}
