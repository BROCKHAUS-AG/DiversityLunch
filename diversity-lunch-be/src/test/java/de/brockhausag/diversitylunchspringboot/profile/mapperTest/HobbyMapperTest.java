package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.HobbyTestDataFactory;
import de.brockhausag.diversitylunchspringboot.profile.logic.HobbyCategoryService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.HobbyCategoryMapper;
import de.brockhausag.diversitylunchspringboot.profile.mapper.HobbyMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.StreamUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HobbyMapperTest {

    @Mock
    private HobbyCategoryService hobbyCategoryService;
    @Mock
    private HobbyCategoryMapper hobbyCategoryMapper;
    @InjectMocks
    private HobbyMapper hobbyMapper;
    private HobbyTestDataFactory factory;

    @BeforeEach
    void setup() {
        this.factory = new HobbyTestDataFactory();
    }

    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity() {
        //Arrange
        HobbyDto inputDto = this.factory.buildDto(1);
        HobbyEntity expectedEntity = this.factory.buildEntity(1);

        when(this.hobbyCategoryService.getEntityById(expectedEntity.getQuestionCategory().getId())).thenReturn(Optional.of(expectedEntity.getQuestionCategory()));

        //Act
        Optional<HobbyEntity> hobbyEntityOptional = this.hobbyMapper.dtoToEntity(inputDto);

        //Assert
        assertTrue(hobbyEntityOptional.isPresent());
        assertEquals(expectedEntity, hobbyEntityOptional.get());
    }

    @Test
    void testDtoToEntity_withOneBrokenDto_returnsOneEmptyOptional(){
        //Arrange
        HobbyDto inputDto = this.factory.buildDtoWithoutCategory();

        //Act
        Optional<HobbyEntity> actual = this.hobbyMapper.dtoToEntity(inputDto);

        //Assert
        assertTrue(actual.isEmpty());
    }
    @Test
    void testDtoToEntity_withDtoWithWrongCategoryId_returnsOneEmptyOptional(){
        //Arrange
        HobbyDto inputDto = this.factory.buildDto(1);
        Long wrongId = 99L;
        inputDto.getCategory().setId(wrongId);

        when(this.hobbyCategoryService.getEntityById(wrongId)).thenReturn(Optional.empty());

        //Act
        Optional<HobbyEntity> actual = this.hobbyMapper.dtoToEntity(inputDto);

        //Assert
        assertTrue(actual.isEmpty());
    }

    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList() {
        //Arrange
        List<HobbyEntity> inputList = Collections.emptyList();

        //Act
        List<HobbyDto> actualList = this.hobbyMapper.entityToDto(inputList);

        //Assert
        assertTrue(actualList.isEmpty());
    }

    @Test
    void testEntityToDto_withOneEntity_returnsOneDto() {
        //Arrange
        HobbyEntity inputEntity = this.factory.buildEntity(1);
        HobbyDto expectedDto = this.factory.buildDto(1);
        when(this.hobbyCategoryMapper.entityToDto(inputEntity.getQuestionCategory())).thenReturn(expectedDto.getCategory());

        //Act
        HobbyDto actualDto = this.hobbyMapper.entityToDto(inputEntity);

        //Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos() {
        //Arrange
        List<HobbyEntity> inputEntities = Stream.of(1, 2, 3).map(this.factory::buildEntity).toList();
        List<HobbyDto> expectedDtos = Stream.of(1, 2, 3).map(this.factory::buildDto).toList();

        StreamUtils.zip(inputEntities.stream(), expectedDtos.stream(), (entity, dto) -> {
            when(this.hobbyCategoryMapper.entityToDto(entity.getQuestionCategory())).thenReturn(dto.getCategory());
            return null;
        }).forEach(unused->{});

        //Act
        List<HobbyDto> actualDtos = this.hobbyMapper.entityToDto(inputEntities);

        //Assert
        assertEquals(3, actualDtos.size());
        assertEquals(expectedDtos, actualDtos);
    }
}
