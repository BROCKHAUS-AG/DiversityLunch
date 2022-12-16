package de.brockhausag.diversitylunchspringboot.profile.controllerTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.HobbyTestDataFactory;
import de.brockhausag.diversitylunchspringboot.profile.controller.HobbyController;
import de.brockhausag.diversitylunchspringboot.profile.logic.HobbyService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.HobbyMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HobbyControllerTest {

    private final Long notExistingId = 42L;
    @Mock
    private HobbyService hobbyService;
    @Mock
    private HobbyMapper mapper;

    @InjectMocks
    private HobbyController hobbyController;

    private HobbyTestDataFactory factory;

    @BeforeEach
    void setUp() {
        this.factory = new HobbyTestDataFactory();
    }

    @Test
    void testGet_withValidId_returnsOkWithHobbyDto() {
        //Arrange
        HobbyEntity inputEntity = this.factory.buildEntity(1);
        HobbyDto expectedDto = this.factory.buildDto(1);

        when(mapper.entityToDto(inputEntity)).thenReturn(expectedDto);
        when(hobbyService.getEntityById(inputEntity.getId())).thenReturn(Optional.of(inputEntity));

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.getOne(inputEntity.getId());

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
    }

    @Test
    void testGet_withNotExistingId_returnsNotFound() {
        //Arrange

        when(hobbyService.getEntityById(notExistingId)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.getOne(notExistingId);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDelete_withExistingId_returnsOk() {
        //Arrange
        final Long existingId = 42L;

        when(hobbyService.deleteEntityById(existingId)).thenReturn(true);

        //Act
        ResponseEntity<?> response = hobbyController.deleteOne(existingId);

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDelete_withNotExistingId_returnsNotFound() {
        //Arrange
        when(hobbyService.deleteEntityById(notExistingId)).thenReturn(false);

        //Act
        ResponseEntity<?> response = hobbyController.deleteOne(notExistingId);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAll_withNoValuesInDatabase_returnsEmptyList() {
        when(this.mapper.entityToDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        when(hobbyService.getAllEntities()).thenReturn(Collections.emptyList());

        //Act
        ResponseEntity<List<HobbyDto>> response = hobbyController.getAll();

        //Assert
        assert response.getBody() != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    void testGetAll_withThreeValuesInDatabase_returnsListOfThreeDtos() {
        //Arrange
        List<HobbyEntity> inputEntities = Stream.of(1, 2, 3).map(this.factory::buildEntity).toList();
        List<HobbyDto> expectedDtos = this.factory.buildDtoList(3);
        when(this.mapper.entityToDto(inputEntities)).thenReturn(expectedDtos);

        when(hobbyService.getAllEntities()).thenReturn(inputEntities);

        //Act
        ResponseEntity<List<HobbyDto>> response = hobbyController.getAll();

        //Assert
        assert response.getBody() != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDtos.size(), response.getBody().size());
        assertEquals(expectedDtos, response.getBody());
    }

    @Test
    void testGetSelection_withNoValuesInDatabase_returnsEmptyList() {
        List<Long> inputIds = Stream.of(30L, 40L, 42L).toList();

        when(this.mapper.entityToDto(Collections.emptyList())).thenReturn(Collections.emptyList());
        when(hobbyService.getEntitySelectionByIds(inputIds)).thenReturn(Collections.emptyList());

        //Act
        ResponseEntity<List<HobbyDto>> response = hobbyController.getSelection(inputIds);

        //Assert
        assert response.getBody() != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    void testGetSelection_withThreeValuesInDatabase_returnsListOfThreeDtos() {
        //Arrange
        List<Long> inputIds = Stream.of(30L, 40L, 42L).toList();
        List<HobbyEntity> inputEntities = Stream.of(1, 2, 3).map(this.factory::buildEntity).toList();
        List<HobbyDto> expectedDtos = this.factory.buildDtoList(3);
        when(this.mapper.entityToDto(inputEntities)).thenReturn(expectedDtos);

        when(hobbyService.getEntitySelectionByIds(inputIds)).thenReturn(inputEntities);

        //Act
        ResponseEntity<List<HobbyDto>> response = hobbyController.getSelection(inputIds);

        //Assert
        assert response.getBody() != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDtos.size(), response.getBody().size());
        assertEquals(expectedDtos, response.getBody());
    }
}
