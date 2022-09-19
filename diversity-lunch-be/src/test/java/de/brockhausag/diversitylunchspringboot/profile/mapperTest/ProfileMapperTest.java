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
    void testDtoToEntity_withOneEntity_returnsOneDto() {
        //Arrange
        ProfileDto inputDto = factory.buildDto(1);
        ProfileEntity expectedEntity = factory.buildEntity(1);

        //Act
        ProfileEntity actualEntity = profileMapper.dtoToEntity(inputDto);

        //Assert
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void testMapEntityToDto() {

    }
}
