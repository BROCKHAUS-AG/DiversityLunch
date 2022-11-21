package de.brockhausag.diversitylunchspringboot.profile.controllerTest;

import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.profile.controller.HobbyController;
import de.brockhausag.diversitylunchspringboot.profile.logic.HobbyService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.HobbyMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import de.brockhausag.diversitylunchspringboot.dataFactories.HobbyTestDataFactory;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HobbyControllerTest {

    @Mock
    private HobbyService hobbyService;

    @Mock
    private HobbyMapper mapper;


    @InjectMocks
    private HobbyController hobbyController;

    private final HobbyTestDataFactory factory = new HobbyTestDataFactory();

    @Test
    void testGetHobby_withValidId_returnsOkWithHobbyDto() {
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
    void testGetHobby_withNotExistingId_returnsNotFound() {
        //Arrange
        final Long notExistingId = 666L;

        when(hobbyService.getEntityById(notExistingId)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.get(notExistingId);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testPostHobby_serviceCreatesEntity_returnsOkWithHobbyDto() {
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
    void testPostHobby_serviceReturnsEmpty_returnsBadRequest() {
        //Arrange
        HobbyDto inputDto = this.factory.buildDto(1);

        when(mapper.dtoToEntity(inputDto)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.post(inputDto);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testPutHobby_serviceReturnsUpdatedDTO_returnsStatusOK() {
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
}
