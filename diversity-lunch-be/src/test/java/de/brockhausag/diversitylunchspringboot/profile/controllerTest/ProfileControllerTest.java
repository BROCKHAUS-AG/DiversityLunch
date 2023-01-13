package de.brockhausag.diversitylunchspringboot.profile.controllerTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.controller.ProfileController;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper.ProfileMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {

    private final ProfileTestdataFactory factory = new ProfileTestdataFactory();
    private final Long accountId = 5L;
    @Mock
    private ProfileService profileService;
    @Mock
    private ProfileMapper mapper;
    @InjectMocks
    private ProfileController profileController;

    @Test
    void testGetProfile_withValidId_returnsOkWithProfileDto() {
        //Arrange
        ProfileEntity inputEntity = this.factory.buildEntity(1);
        ProfileDto expectedDto = this.factory.buildDto(1);

        when(mapper.entityToDto(inputEntity)).thenReturn(expectedDto);
        when(profileService.getProfile(inputEntity.getId())).thenReturn(Optional.of(inputEntity));

        //Act
        ResponseEntity<ProfileDto> response = profileController.getProfile(inputEntity.getId());

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
    }

    @Test
    void testGetProfile_withNotExistingId_returnsNotFound() {
        //Arrange
        final Long notExistingId = 666L;

        when(profileService.getProfile(notExistingId)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<ProfileDto> response = profileController.getProfile(notExistingId);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testPostProfile_serviceCreatesEntity_returnsOkWithProfileDto() {
        //Arrange
        ProfileDto inputDto = this.factory.buildDto(1);
        ProfileEntity profileEntity = this.factory.buildEntity(1);
        ProfileDto expectedDto = this.factory.buildDto(1);


        when(mapper.dtoToEntity(inputDto)).thenReturn(profileEntity);
        when(profileService.createProfile(profileEntity, accountId)).thenReturn(Optional.of(profileEntity));
        when(mapper.entityToDto(profileEntity)).thenReturn(expectedDto);

        //Act
        ResponseEntity<ProfileDto> response = profileController.createProfile(inputDto, accountId);

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
    }

    @Test
    void testPostProfile_serviceReturnsEmpty_returnsBadRequest() {
        //Arrange
        ProfileDto inputDto = this.factory.buildDto(1);
        ProfileEntity profileEntity = this.factory.buildEntity(1);

        when(mapper.dtoToEntity(inputDto)).thenReturn(profileEntity);
        when(profileService.createProfile(profileEntity, accountId)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<ProfileDto> response = profileController.createProfile(inputDto, accountId);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testPutProfile_serviceReturnsUpdatedDTO_returnsStatusOK() {
        //Arrange
        ProfileDto inputDto = this.factory.buildDto(1);
        ProfileEntity profileEntity = this.factory.buildEntity(1);

        when(mapper.dtoToEntity(inputDto)).thenReturn(profileEntity);
        when(profileService.updateProfile(profileEntity)).thenReturn(Optional.of(profileEntity));

        //Act
        ResponseEntity<ProfileDto> response = profileController.updateProfile(profileEntity.getId(), inputDto);

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testPutProfile_serviceReturnsEmpty_returnsBadRequest() {
        //Arrange
        final Long notMatchingId = 95L;
        ProfileDto inputDto = this.factory.buildDto(1);

        //Act
        ResponseEntity<ProfileDto> response = profileController.updateProfile(notMatchingId, inputDto);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testPutProfile_serviceReturnsBadRequest() {
        //Arrange
        ProfileEntity profileEntity = factory.buildEntity(1);
        ProfileDto inputDto = factory.buildDto(1);

        when(mapper.dtoToEntity(inputDto)).thenReturn(profileEntity);
        when(profileService.updateProfile(profileEntity)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<ProfileDto> response = profileController.updateProfile(profileEntity.getId(), inputDto);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}

