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
        ResponseEntity<HobbyDto> response = hobbyController.get(inputEntity.getId());

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
    }

    @Test
    void testGet_withNotExistingId_returnsNotFound() {
        //Arrange

        when(hobbyService.getEntityById(notExistingId)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.get(notExistingId);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testPost_withExistingCategory_returnsOkWithHobbyDto() {
        //Arrange
        HobbyDto inputDto = this.factory.buildDto(1);
        HobbyEntity hobbyEntity = this.factory.buildEntity(1);
        HobbyDto expectedDto = this.factory.buildDto(1);


        when(mapper.dtoToEntity(inputDto)).thenReturn(Optional.of(hobbyEntity));
        when(hobbyService.createEntity(hobbyEntity)).thenReturn(hobbyEntity);
        when(mapper.entityToDto(hobbyEntity)).thenReturn(expectedDto);

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.post(inputDto);

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
    }

    @Test
    void testPost_withNotExistingCategory_returnsBadRequest() {
        //Arrange
        HobbyDto inputDto = this.factory.buildDto(1);

        when(mapper.dtoToEntity(inputDto)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.post(inputDto);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testPut_withExistingCategory_returnsStatusOK() {
        //Arrange
        HobbyDto inputDto = this.factory.buildDto(1);
        HobbyEntity hobbyEntity = this.factory.buildEntity(1);

        when(mapper.dtoToEntity(inputDto)).thenReturn(Optional.of(hobbyEntity));
        when(hobbyService.updateOrCreateEntity(hobbyEntity)).thenReturn(hobbyEntity);

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.put(inputDto);

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testPut_withNotExistingCategory_returnsBadRequest() {
        //Arrange
        HobbyDto inputDto = this.factory.buildDto(1);

        when(mapper.dtoToEntity(inputDto)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.put(inputDto);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testDelete_withExistingId_returnsOk(){
        //Arrange
        final Long existingId = 42L;

        when(hobbyService.deleteEntityById(existingId)).thenReturn(true);

        //Act
        ResponseEntity<?> response = hobbyController.delete(existingId);

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDelete_withNotExistingId_returnsNotFound(){
        //Arrange
        when(hobbyService.deleteEntityById(notExistingId)).thenReturn(false);

        //Act
        ResponseEntity<?> response = hobbyController.delete(notExistingId);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAll_withNoValuesInDatabase_returnsEmptyList(){
        when(this.mapper.entityToDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        when(hobbyService.getAllEntities()).thenReturn(Collections.emptyList());

        //Act
        ResponseEntity<List<HobbyDto>> response = hobbyController.getAll();

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0,response.getBody().size());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    void testGetAll_withThreeValuesInDatabase_returnsListOfThreeDtos(){
        //Arrange
        List<HobbyEntity> inputEntities = Stream.of(1, 2, 3).map(this.factory::buildEntity).toList();
        List<HobbyDto> expectedDtos = Stream.of(1, 2, 3).map(this.factory::buildDto).toList();
        when(this.mapper.entityToDto(inputEntities)).thenReturn(expectedDtos);

        when(hobbyService.getAllEntities()).thenReturn(inputEntities);

        //Act
        ResponseEntity<List<HobbyDto>> response = hobbyController.getAll();

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDtos.size(),response.getBody().size());
        assertEquals(expectedDtos, response.getBody());
    }
}
