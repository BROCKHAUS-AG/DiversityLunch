

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

    @Mock
    private AccountService accountService;

    @InjectMocks
    private HobbyController hobbyController;

    private final HobbyTestDataFactory factory = new HobbyTestDataFactory();
    private final Long accountId = 5L;

    @Test
    void testGetHobby_withValidId_returnsOkWithHobbyDto() {
        //Arrange
        HobbyEntity inputEntity = this.factory.buildEntity(1);
        HobbyDto expectedDto = this.factory.buildDto(1);

        when(mapper.entityToDto(inputEntity)).thenReturn(expectedDto);
        when(hobbyService.getHobby(inputEntity.getId())).thenReturn(Optional.of(inputEntity));

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.getHobby(inputEntity.getId());

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
    }

    @Test
    void testGetHobby_withNotExistingId_returnsNotFound() {
        //Arrange
        final Long notExistingId = 666L;

        when(hobbyService.getHobby(notExistingId)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.getHobby(notExistingId);

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
        when(hobbyService.createHobby(hobbyEntity, accountId)).thenReturn(Optional.of(hobbyEntity));
        when(mapper.entityToDto(hobbyEntity)).thenReturn(expectedDto);

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.createHobby(inputDto, accountId);

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
    }

    @Test
    void testPostHobby_serviceReturnsEmpty_returnsBadRequest() {
        //Arrange
        HobbyDto inputDto = this.factory.buildDto(1);
        HobbyEntity hobbyEntity = this.factory.buildEntity(1);

        when(mapper.dtoToEntity(inputDto)).thenReturn(Optional.of(hobbyEntity));
        when(hobbyService.createHobby(hobbyEntity, accountId)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.createHobby(inputDto, accountId);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testPutHobby_serviceReturnsUpdatedDTO_returnsStatusOK(){
        //Arrange
        HobbyDto inputDto = this.factory.buildDto(1);
        HobbyEntity hobbyEntity = this.factory.buildEntity(1);

        when(mapper.dtoToEntity(inputDto)).thenReturn(Optional.of(hobbyEntity));
        when(hobbyService.updateHobby(hobbyEntity)).thenReturn(Optional.of(hobbyEntity));

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.updateHobby(hobbyEntity.getId(), inputDto);

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testPutHobby_serviceReturnsEmpty_returnsBadRequest() {
        //Arrange
        final Long notMatchingId = 95L;
        HobbyDto inputDto = this.factory.buildDto(1);

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.updateHobby(notMatchingId, inputDto);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testPutHobby_serviceReturnsBadRequest() {
        //Arrange
        HobbyEntity hobbyEntity = factory.buildEntity(1);
        HobbyDto inputDto = factory.buildDto(1);

        when(mapper.dtoToEntity(inputDto)).thenReturn(Optional.of(hobbyEntity));
        when(hobbyService.updateHobby(hobbyEntity)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<HobbyDto> response = hobbyController.updateHobby(hobbyEntity.getId(), inputDto);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
