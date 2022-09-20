package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.profile.mapper.*;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileMapperTest {


    @InjectMocks
    private ProfileMapper profileMapper;
    private ProfileTestdataFactory factory;

    @BeforeEach
    void setup(){
        this.factory = new ProfileTestdataFactory();
    }

    @Test
    void testDtoToEntity_withEmptyDtoList_returnsEmptyEntityList(){}

    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity() {
        //Arrange
        ProfileDto inputDto = factory.buildDto(1);
        ProfileEntity expectedEntity = factory.buildEntity(1);

        //Act
        ProfileEntity actualEntity = profileMapper.dtoToEntity(inputDto);

        //Assert
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void testDtoToEntity_withListOfThreeDtos_returnsListOfThreeEntities(){
        //Arrange
        List<ProfileDto> input = List.of(factory.buildDto(1),factory.buildDto(2),factory.buildDto(3));
        List<ProfileEntity> expected = List.of(factory.buildEntity(1),factory.buildEntity(2),factory.buildEntity(3));

        //Act
        List<ProfileEntity> result = profileMapper.dtoToEntity(input);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList(){}
    @Test
    void testEntityToDto_withOneEntity_returnsOneDto(){}
    @Test
    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos(){}
}
