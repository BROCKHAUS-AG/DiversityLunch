package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.profile.mapper.DietMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.DietEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DietMapperTest {

    private DietEntity firstEntity;
    private DietEntity secondEntity;
    private DietEntity thirdEntity;

    private DietDto firstDto;
    private DietDto secondDto;
    private DietDto thirdDto;


    private DietMapper dietMapper;

    @BeforeEach
    void setup() {
        final Long firstId = 1L, secondId = 2L, thirdId = 3L;
        final String firstDescriptor = "First Object", secondDescriptor = "Second Object",
                thirdDescriptor = "Third Object";

        firstEntity = new DietEntity(firstId, firstDescriptor);
        secondEntity = new DietEntity(secondId, secondDescriptor);
        thirdEntity = new DietEntity(thirdId, thirdDescriptor);

        firstDto = new DietDto(firstId, firstDescriptor);
        secondDto = new DietDto(secondId, secondDescriptor);
        thirdDto = new DietDto(thirdId, thirdDescriptor);

        dietMapper = new DietMapper();
    }

    @Test
    void testEntityToDto_withOneEntity_returnsOneDto() {
        //Arrange
        DietEntity inputEntity = firstEntity;
        DietDto expectedDto = firstDto;

        //Act
        DietDto actualDto = dietMapper.entityToDto(inputEntity);

        //Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos() {
        //Arrange
        List<DietEntity> inputEntityList = Arrays.asList(firstEntity, secondEntity, thirdEntity);
        List<DietDto> expectedDtoList = Arrays.asList(firstDto, secondDto, thirdDto);

        //Act
        List<DietDto> actualDtoList = dietMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList() {
        //Arrange
        List<DietDto> expectedDtoList = Collections.emptyList();
        List<DietEntity> inputEntityList = Collections.emptyList();

        //Act
        List<DietDto> actualDtoList = dietMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity() {
        //Arrange
        DietDto inputDto = firstDto;
        DietEntity expectedEntity = firstEntity;


        //Act
        DietEntity actualEntity = dietMapper.dtoToEntity(inputDto);

        //Assert
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void testDtoToEntity_withListOfTwoDtos_returnsListOfTwoEntities() {
        //Arrange
        List<DietEntity> expectedEntityList = Arrays.asList(firstEntity, secondEntity, thirdEntity);
        List<DietDto> inputDtoList = Arrays.asList(firstDto, secondDto, thirdDto);

        //Act
        List<DietEntity> actualEntityList = dietMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }

    @Test
    void testDtoToEntity_withEmptyDtoList_returnsEmptyEntityList() {
        //Arrange
        List<DietDto> inputDtoList = Collections.emptyList();
        List<DietEntity> expectedEntityList = Collections.emptyList();

        //Act
        List<DietEntity> actualEntityList = dietMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }


}
