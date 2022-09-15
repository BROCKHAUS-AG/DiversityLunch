package de.brockhausag.diversitylunchspringboot.profile.mapperTest;


import de.brockhausag.diversitylunchspringboot.profile.modelTest.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.modelTest.entities.CountryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryMapperTest {

    private CountryEntity firstEntity;
    private CountryEntity secondEntity;
    private CountryEntity thirdEntity;

    private CountryDto firstDto;
    private CountryDto secondDto;
    private CountryDto thirdDto;


    private CountryMapper countryMapper;
    @BeforeEach
    void setup(){
        final Long firstId = 1L, secondId = 2L, thirdId = 3L;
        final String firstDescriptor = "First Object", secondDescriptor = "Second Object",
                thirdDescriptor = "Third Object";

        firstEntity = new CountryEntity(firstId, firstDescriptor);
        secondEntity = new CountryEntity(secondId, secondDescriptor);
        thirdEntity = new CountryEntity(thirdId, thirdDescriptor);

        firstDto = new CountryDto(firstId, firstDescriptor);
        secondDto = new CountryDto(secondId, secondDescriptor);
        thirdDto = new CountryDto(thirdId, thirdDescriptor);

        countryMapper = new CountryMapper();
    }

    @Test
    void testEntityToDto_withOneEntity_returnsOneDto(){
        //Arrange
        CountryEntity inputEntity = firstEntity;
        CountryDto expectedDto = firstDto;

        //Act
        CountryDto actualDto = countryMapper.entityToDto(inputEntity);

        //Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos(){
        //Arrange
        List<CountryEntity> inputEntityList = Arrays.asList(firstEntity, secondEntity, thirdEntity);
        List<CountryDto> expectedDtoList = Arrays.asList(firstDto, secondDto, thirdDto);

        //Act
        List<CountryDto> actualDtoList = countryMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList(){
        //Arrange
        List<CountryDto> expectedDtoList = Collections.emptyList();
        List<CountryEntity> inputEntityList = Collections.emptyList();

        //Act
        List<CountryDto> actualDtoList = countryMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity(){
        //Arrange
        CountryDto inputDto = firstDto;
        CountryEntity expectedEntity = firstEntity;


        //Act
        CountryEntity actualEntity = countryMapper.dtoToEntity(inputDto);

        //Assert
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void testDtoToEntity_withListOfTwoDtos_returnsListOfTwoEntities(){
        //Arrange
        List<CountryEntity> expectedEntityList = Arrays.asList(firstEntity, secondEntity, thirdEntity);
        List<CountryDto> inputDtoList = Arrays.asList(firstDto, secondDto, thirdDto);

        //Act
        List<CountryEntity> actualEntityList = countryMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }

    @Test
    void testDtoToEntity_withEmptyDtoList_returnsEmptyEntityList(){
        //Arrange
        List<CountryDto> inputDtoList = Collections.emptyList();
        List<CountryEntity> expectedEntityList = Collections.emptyList();

        //Act
        List<CountryEntity> actualEntityList = countryMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }


}
