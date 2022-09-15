package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.data.factoriesForMapperTests.DietTestDataFactory;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.DietEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DietMapperTest {

    private DietMapper dietMapper;
    private DietTestDataFactory factory;

    @BeforeEach
    void setup(){
        dietMapper = new DietMapper();
        factory = new DietTestDataFactory();
    }

    @Test
    void testEntityToDto_withOneEntity_returnsOneDto(){
        //Arrange
        DietEntity inputEntity = this.factory.buildDietEntity();
        DietDto expectedDto = this.factory.buildDietDto();

        //Act
        DietDto actualDto = dietMapper.entityToDto(inputEntity);

        //Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testEntityToDto_withListOfTwoEntities_returnsListOfTwoDtos(){
        //Arrange
        DietEntity firstDietEntity = this.factory.buildDietEntity();
        DietEntity secondDietEntity = this.factory.buildSecondDietEntity();
        List<DietEntity> inputEntityList = Arrays.asList(firstDietEntity, secondDietEntity);

        DietDto firstDietDto = this.factory.buildDietDto();
        DietDto secondDietDto = this.factory.buildSecondDietDto();
        List<DietDto> expectedDtoList = Arrays.asList(firstDietDto, secondDietDto);

        //Act
        List<DietDto> actualDtoList = dietMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList(){
        //Arrange
        List<DietDto> expectedDtoList = Collections.emptyList();
        List<DietEntity> inputEntityList = Collections.emptyList();

        //Act
        List<DietDto> actualDtoList = dietMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity(){
        //Arrange
        DietDto inputDto = factory.buildDietDto();
        DietEntity expectedEntity = factory.buildDietEntity();


        //Act
        DietEntity actualEntity = dietMapper.dtoToEntity(inputDto);

        //Assert
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void testDtoToEntity_withListOfTwoDtos_returnsListOfTwoEntities(){
        //Arrange
        DietEntity firstDietEntity = this.factory.buildDietEntity();
        DietEntity secondDietEntity = this.factory.buildSecondDietEntity();
        List<DietEntity> expectedEntityList = Arrays.asList(firstDietEntity, secondDietEntity);

        DietDto firstDietDto = this.factory.buildDietDto();
        DietDto secondDietDto = this.factory.buildSecondDietDto();
        List<DietDto> inputDtoList = Arrays.asList(firstDietDto, secondDietDto);

        //Act
        List<DietEntity> actualEntityList = dietMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }

    @Test
    void testDtoToEntity_withEmptyDtoList_returnsEmptyEntityList(){
        //Arrange
        List<DietDto> inputDtoList = Collections.emptyList();
        List<DietEntity> expectedEntityList = Collections.emptyList();

        //Act
        List<DietEntity> actualEntityList = dietMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }

}
