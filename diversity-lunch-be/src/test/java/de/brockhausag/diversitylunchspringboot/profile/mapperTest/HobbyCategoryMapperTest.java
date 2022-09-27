package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.HobbyCategoryTestDataFactory;
import de.brockhausag.diversitylunchspringboot.profile.mapper.HobbyCategoryMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyCategoryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyCategoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HobbyCategoryMapperTest {

    @InjectMocks
    private HobbyCategoryMapper mapper;
    private HobbyCategoryTestDataFactory factory;

    @BeforeEach
    void setup(){
        this.factory = new HobbyCategoryTestDataFactory();
        this.mapper = new HobbyCategoryMapper();
    }

    @Test
    void testEntityToDto_withOneEntity_returnsOneDto(){
        //Arrange
        HobbyCategoryEntity inputEntity = factory.buildEntity(1);
        HobbyCategoryDto expectedDto = factory.buildDto(1);

        //Act
        HobbyCategoryDto actualDto = mapper.entityToDto(inputEntity);

        //Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos(){
        //Arrange
        List<HobbyCategoryEntity> inputEntityList = Arrays.asList(factory.buildEntity(1), factory.buildEntity(2), factory.buildEntity(3));
        List<HobbyCategoryDto> expectedDtoList = Arrays.asList(factory.buildDto(1), factory.buildDto(2), factory.buildDto(3));

        //Act
        List<HobbyCategoryDto> actualDtoList = mapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList(){
        //Arrange
        List<HobbyCategoryDto> expectedDtoList = Collections.emptyList();
        List<HobbyCategoryEntity> inputEntityList = Collections.emptyList();

        //Act
        List<HobbyCategoryDto> actualDtoList = mapper.entityToDto(inputEntityList);

        //Assert
        assertEquals(expectedDtoList, actualDtoList);
    }

    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity(){
        //Arrange
        HobbyCategoryDto inputDto = factory.buildDto(1);
        HobbyCategoryEntity expectedEntity = factory.buildEntity(1);


        //Act
        HobbyCategoryEntity actualEntity = mapper.dtoToEntity(inputDto);

        //Assert
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void testDtoToEntity_withListOfTwoDtos_returnsListOfTwoEntities(){
        //Arrange
        List<HobbyCategoryEntity> expectedEntityList = Arrays.asList(factory.buildEntity(1), factory.buildEntity(2), factory.buildEntity(3));
        List<HobbyCategoryDto> inputDtoList = Arrays.asList(factory.buildDto(1), factory.buildDto(2), factory.buildDto(3));
        //Act
        List<HobbyCategoryEntity> actualEntityList = mapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }

    @Test
    void testDtoToEntity_withEmptyDtoList_returnsEmptyEntityList(){
        //Arrange
        List<HobbyCategoryDto> inputDtoList = Collections.emptyList();
        List<HobbyCategoryEntity> expectedEntityList = Collections.emptyList();

        //Act
        List<HobbyCategoryEntity> actualEntityList = mapper.dtoToEntity(inputDtoList);

        //Assert
        assertEquals(expectedEntityList, actualEntityList);
    }


}
