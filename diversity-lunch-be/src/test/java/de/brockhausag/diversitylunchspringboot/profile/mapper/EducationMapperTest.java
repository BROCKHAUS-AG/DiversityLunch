package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.data.EducationTestDataFactory;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EducationMapperTest {

    private EducationMapper educationMapper;
    private EducationTestDataFactory factory;

    @BeforeEach
    void setup(){
        educationMapper = new EducationMapper(new EducationDto(), new EducationEntity());
        factory = new EducationTestDataFactory();
    }

    @Test
    void testEntityToDto_withOneEntity_returnsOneDto(){
        //Arrange
        EducationEntity inputEntity = this.factory.buildEducationEntity();
        EducationDto expectedDto = this.factory.buildEducationDto();

        //Act
        EducationDto actualDto = educationMapper.entityToDto(inputEntity);

        //Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testEntityToDto_withListOfTwoEntities_returnsListOfTwoDtos(){
        //Arrange
        EducationEntity firstEducationEntity = this.factory.buildEducationEntity();
        EducationEntity secondEducationEntity = this.factory.buildSecondEducationEntity();
        List<EducationEntity> inputEntityList = Arrays.asList(firstEducationEntity, secondEducationEntity);

        EducationDto firstEducationDto = this.factory.buildEducationDto();
        EducationDto secondEducationDto = this.factory.buildSecondEducationDto();
        List<EducationDto> expectedDtoList = Arrays.asList(firstEducationDto, secondEducationDto);

        //Act
        List<EducationDto> actualDtoList = educationMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList(){
        //Arrange
        List<EducationDto> expectedDtoList = Collections.emptyList();
        List<EducationEntity> inputEntityList = Collections.emptyList();

        //Act
        List<EducationDto> actualDtoList = educationMapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity(){
        //Arrange
        EducationDto inputDto = factory.buildEducationDto();
        EducationEntity expectedEntity = factory.buildEducationEntity();


        //Act
        EducationEntity actualEntity = educationMapper.dtoToEntity(inputDto);

        //Assert
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void testDtoToEntity_withListOfTwoDtos_returnsListOfTwoEntities(){
        //Arrange
        EducationEntity firstEducationEntity = this.factory.buildEducationEntity();
        EducationEntity secondEducationEntity = this.factory.buildSecondEducationEntity();
        List<EducationEntity> expectedEntityList = Arrays.asList(firstEducationEntity, secondEducationEntity);

        EducationDto firstEducationDto = this.factory.buildEducationDto();
        EducationDto secondEducationDto = this.factory.buildSecondEducationDto();
        List<EducationDto> inputDtoList = Arrays.asList(firstEducationDto, secondEducationDto);

        //Act
        List<EducationEntity> actualEntityList = educationMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }

    @Test
    void testDtoToEntity_withEmptyDtoList_returnsEmptyEntityList(){
        //Arrange
        List<EducationDto> inputDtoList = Collections.emptyList();
        List<EducationEntity> expectedEntityList = Collections.emptyList();

        //Act
        List<EducationEntity> actualEntityList = educationMapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }

}
