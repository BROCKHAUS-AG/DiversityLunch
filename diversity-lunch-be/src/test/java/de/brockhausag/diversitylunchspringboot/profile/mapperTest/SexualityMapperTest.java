package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.profile.mapper.SexualityMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SexualityDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualityEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SexualityMapperTest {

    private SexualityEntity firstEntity;
    private SexualityEntity secondEntity;
    private SexualityEntity thirdEntity;

    private SexualityDto firstDto;
    private SexualityDto secondDto;
    private SexualityDto thirdDto;


    private SexualityMapper sexualityMapper;

    @BeforeEach
    void setup(){
        final Long firstId = 1L, secondId = 2L, thirdId = 3L;
        final String firstDescriptor = "First Object", secondDescriptor = "Second Object",
                thirdDescriptor = "Third Object";

        firstEntity = new SexualityEntity(firstId, firstDescriptor);
        secondEntity = new SexualityEntity(secondId, secondDescriptor);
        thirdEntity = new SexualityEntity(thirdId, thirdDescriptor);

        firstDto = new SexualityDto(firstId, firstDescriptor);
        secondDto = new SexualityDto(secondId, secondDescriptor);
        thirdDto = new SexualityDto(thirdId, thirdDescriptor);

        sexualityMapper = new SexualityMapper();
    }

    @Test
    void testEntityToDto_withOneEntity_returnsOneDto(){
        //Arrange
        SexualityEntity inputEntity = firstEntity;
        SexualityDto expectedDto = firstDto;

        //Act
        SexualityDto actualDto = sexualityMapper.entityToDto(inputEntity);

        //Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos(){
        //Arrange
        List<SexualityEntity> inputEntityList = Arrays.asList(firstEntity, secondEntity, thirdEntity);
        List<SexualityDto> expectedDtoList = Arrays.asList(firstDto, secondDto, thirdDto);

        //Act
        List<SexualityDto> actualDtoList = sexualityMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }


    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList(){
        //Arrange
        List<SexualityDto> expectedDtoList = Collections.emptyList();
        List<SexualityEntity> inputEntityList = Collections.emptyList();

        //Act
        List<SexualityDto> actualDtoList = sexualityMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity(){
        //Arrange
        SexualityDto inputDto = firstDto;
        SexualityEntity expectedEntity = firstEntity;


        //Act
        SexualityEntity actualEntity = sexualityMapper.dtoToEntity(inputDto);

        //Assert
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void testDtoToEntity_withListOfTwoDtos_returnsListOfTwoEntities(){
        //Arrange
        List<SexualityEntity> expectedEntityList = Arrays.asList(firstEntity, secondEntity, thirdEntity);
        List<SexualityDto> inputDtoList = Arrays.asList(firstDto, secondDto, thirdDto);

        //Act
        List<SexualityEntity> actualEntityList = sexualityMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }

    @Test
    void testDtoToEntity_withEmptyDtoList_returnsEmptyEntityList(){
        //Arrange
        List<SexualityDto> inputDtoList = Collections.emptyList();
        List<SexualityEntity> expectedEntityList = Collections.emptyList();

        //Act
        List<SexualityEntity> actualEntityList = sexualityMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }

}
