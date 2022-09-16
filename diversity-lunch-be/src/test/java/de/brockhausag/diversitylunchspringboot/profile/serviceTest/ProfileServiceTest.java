package de.brockhausag.diversitylunchspringboot.profile.serviceTest;

import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
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

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    AccountService accountService;

    @InjectMocks
    private ProfileService service;

    private final ProfileTestdataFactory factory = new ProfileTestdataFactory();
    private final long accountId = 42;

    @Test
    void testProfileNonexistent() {
        //Assemble
        long id = 15;
        when(profileRepository.findById(id)).thenReturn(Optional.empty());

        //Act
        Optional<ProfileEntity> profile = service.getProfile(id);

        //Assert
        assertTrue(profile.isEmpty());
    }

    @Test
    void testGetProfile() {
        //Assemble
        ProfileEntity entity = this.factory.entity();
        long id = entity.getId();
        when(profileRepository.findById(id)).thenReturn(Optional.of(entity));

        //Act
        Optional<ProfileEntity> result = service.getProfile(id);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(entity, result.get());
    }

    @Test
    void testCreateProfile() {
        ProfileEntity expected = this.factory.entity();
        ProfileEntity createEntity = this.factory.createEntity();

        when(profileRepository.save(createEntity)).thenReturn(expected);
        when(accountService.updateAccount(expected, accountId)).thenReturn(Optional.empty());

        Optional<ProfileEntity> entity = this.service.createProfile(createEntity, accountId);

        assertTrue(entity.isPresent());
        assertEquals(expected, entity.get());
    }

    @Test
    void testUpdateProfile() {
        ProfileEntity updateEntity = this.factory
                .entityBuilder()
                .email("matthias.reim@schlager.de")
                .build();

        when(this.profileRepository.save(updateEntity)).thenReturn(updateEntity);

        Optional<ProfileEntity> entity = this.service.updateProfile(updateEntity);

        assertTrue(entity.isPresent());
        assertEquals(updateEntity, entity.get());


    }
}
