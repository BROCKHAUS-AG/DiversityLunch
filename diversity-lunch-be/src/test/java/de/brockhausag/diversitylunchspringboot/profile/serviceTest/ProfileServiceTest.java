package de.brockhausag.diversitylunchspringboot.profile.serviceTest;

import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.profile.repository.ProfileRepository;
import de.brockhausag.diversitylunchspringboot.profile.services.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    private final ProfileTestdataFactory factory = new ProfileTestdataFactory();
    @Mock
    AccountService accountService;
    @Mock
    private ProfileRepository profileRepository;
    @InjectMocks
    private ProfileService service;

    @Test
    void testGetProfile_withNotExistingProfile_shouldReturnEmptyOptional() {
        //Assemble
        Long id = 15L;
        when(profileRepository.findById(id)).thenReturn(Optional.empty());

        //Act
        Optional<ProfileEntity> profile = service.getProfile(id);

        //Assert
        assertTrue(profile.isEmpty());
    }

    @Test
    void testGetProfile_withExistingProfile_shouldReturnProfile() {
        //Assemble
        ProfileEntity entity = this.factory.buildEntity(1);
        long id = entity.getId();
        when(profileRepository.findById(id)).thenReturn(Optional.of(entity));

        //Act
        Optional<ProfileEntity> result = service.getProfile(id);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(entity, result.get());
    }

    @Test
    void testCreateProfile_withCompleteProfile_shouldReturnProfile() {
        ProfileEntity expected = this.factory.buildEntity(1);
        ProfileEntity createEntity = this.factory.buildEntity(1);

        when(profileRepository.save(createEntity)).thenReturn(expected);
        long accountId = 42;
        when(accountService.updateAccount(expected, accountId)).thenReturn(Optional.empty());

        Optional<ProfileEntity> entity = this.service.createProfile(createEntity, accountId);

        assertTrue(entity.isPresent());
        assertEquals(expected, entity.get());
    }

    @Test
    void testUpdateProfile_withCompleteAndExistingProfile_shouldReturnProfile() {
        ProfileEntity updateEntity = this.factory.buildEntity(1);

        when(this.profileRepository.existsById(updateEntity.getId())).thenReturn(true);
        when(this.profileRepository.save(updateEntity)).thenReturn(updateEntity);

        Optional<ProfileEntity> entity = this.service.updateProfile(updateEntity);

        assertTrue(entity.isPresent());
        assertEquals(updateEntity, entity.get());
    }
}