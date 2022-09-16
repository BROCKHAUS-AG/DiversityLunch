package de.brockhausag.diversitylunchspringboot.profile.controllerTest;

import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.data.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.profile.controller.ProfileController;
import de.brockhausag.diversitylunchspringboot.profile.model.CreateProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.ProfileMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {

    @Mock
    private ProfileService profileService;

    @Mock
    private ProfileMapper mapper;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private ProfileController profileController;

    private final ProfileTestdataFactory factory = new ProfileTestdataFactory();
    private final long accountId = 5;

    @Test
    void testGetProfile_withValidId_returnsOkWithProfileDto() {
        //ASSEMBLE
        ProfileEntity profileEntity = this.factory.entity();
        ProfileDto profileDto = this.factory.dto();

        when(mapper.mapEntityToDto(profileEntity)).thenReturn(profileDto);
        when(profileService.getProfile(profileEntity.getId())).thenReturn(Optional.of(profileEntity));

        //ACT
        ResponseEntity<ProfileDto> response = profileController.getProfile(profileEntity.getId());

        //ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(profileDto, response.getBody());
    }

    @Test
    void testGetProfile_withInvalidId_returnsNotFound() {
        when(profileService.getProfile(95)).thenReturn(Optional.empty());

        ResponseEntity<ProfileDto> response = profileController.getProfile(95);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testPostProfile_serviceCreatesEntity_returnsOkWithProfileDto() {
        CreateProfileDto createProfileDto = this.factory.createDto();
        ProfileEntity createProfileEntity = this.factory.createEntity();
        ProfileEntity profileEntity = this.factory.entity();
        ProfileDto dto = this.factory.dto();


        when(mapper.mapCreateDtoToEntity(createProfileDto)).thenReturn(createProfileEntity);
        when(profileService.createProfile(createProfileEntity, accountId)).thenReturn(Optional.of(profileEntity));
        when(mapper.mapEntityToDto(profileEntity)).thenReturn(dto);

        ResponseEntity<ProfileDto> response = profileController.createProfile(createProfileDto, accountId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testPostProfile_serviceReturnsEmpty_returnsBadRequest() {
        CreateProfileDto createProfileDto = this.factory.createDto();
        ProfileEntity createProfileEntity = this.factory.createEntity();

        when(mapper.mapCreateDtoToEntity(createProfileDto)).thenReturn(createProfileEntity);
        when(profileService.createProfile(createProfileEntity, accountId)).thenReturn(Optional.empty());

        ResponseEntity<ProfileDto> response = profileController.createProfile(createProfileDto, accountId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testPutProfile_serviceReturnsUpdatedDTO_returnsStatusOK(){
        ProfileDto profileDto = this.factory.dto();
        ProfileEntity profileEntity = this.factory.entity();

        when(mapper.mapDtoToEntity(profileDto)).thenReturn(profileEntity);
        when(profileService.updateProfile(profileEntity)).thenReturn(Optional.of(profileEntity));

        ResponseEntity<ProfileDto> response = profileController.updateProfile(profileEntity.getId(), profileDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testPutProfile_serviceReturnsEmpty_returnsBadRequest() {
        ProfileDto profileDto = this.factory.dto();

        ResponseEntity<ProfileDto> response = profileController.updateProfile(95, profileDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testPutProfile_serviceReturnsBadRequest() {
        ProfileEntity profileEntity = factory.entityBuilder().id(95).build();
        ProfileDto profileDto = factory.dtoBuilder().id(95).build();

        when(mapper.mapDtoToEntity(profileDto)).thenReturn(profileEntity);
        when(profileService.updateProfile(profileEntity)).thenReturn(Optional.empty());

        ResponseEntity<ProfileDto> response = profileController.updateProfile(profileEntity.getId(), profileDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}

