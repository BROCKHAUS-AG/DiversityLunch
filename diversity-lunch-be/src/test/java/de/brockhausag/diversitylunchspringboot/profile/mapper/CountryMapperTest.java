package de.brockhausag.diversitylunchspringboot.profile.mapper;


import de.brockhausag.diversitylunchspringboot.data.CountryTestDataFactory;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.CountryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryMapperTest {

    private CountryMapper countryMapper;
    private CountryTestDataFactory factory;

    @BeforeEach
    void setup(){
        countryMapper = new CountryMapper();
        factory = new CountryTestDataFactory();
    }

    @Test
    void testEntityToDto_withOneEntity_returnsOneDto(){
        //Arrange
        CountryEntity inputEntity = this.factory.buildCountryEntity();
        CountryDto expectedDto = this.factory.buildCountryDto();

        //Act
        CountryDto actualDto = countryMapper.entityToDto(inputEntity);

        //Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testEntityToDto_withListOfTwoEntities_returnsListOfTwoDtos(){
        //Arrange
        CountryEntity firstCountryEntity = this.factory.buildCountryEntity();
        CountryEntity secondCountryEntity = this.factory.buildSecondCountryEntity();
        List<CountryEntity> inputEntityList = Arrays.asList(firstCountryEntity, secondCountryEntity);

        CountryDto firstCountryDto = this.factory.buildCountryDto();
        CountryDto secondCountryDto = this.factory.buildSecondCountryDto();
        List<CountryDto> expectedDtoList = Arrays.asList(firstCountryDto, secondCountryDto);

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
        CountryDto inputDto = factory.buildCountryDto();
        CountryEntity expectedEntity = factory.buildCountryEntity();


        //Act
        CountryEntity actualEntity = countryMapper.dtoToEntity(inputDto);

        //Assert
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void testDtoToEntity_withListOfTwoDtos_returnsListOfTwoEntities(){
        //Arrange
        CountryEntity firstCountryEntity = this.factory.buildCountryEntity();
        CountryEntity secondCountryEntity = this.factory.buildSecondCountryEntity();
        List<CountryEntity> expectedEntityList = Arrays.asList(firstCountryEntity, secondCountryEntity);

        CountryDto firstCountryDto = this.factory.buildCountryDto();
        CountryDto secondCountryDto = this.factory.buildSecondCountryDto();
        List<CountryDto> inputDtoList = Arrays.asList(firstCountryDto, secondCountryDto);

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
