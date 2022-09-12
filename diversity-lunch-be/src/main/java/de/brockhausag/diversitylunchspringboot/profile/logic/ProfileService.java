package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

    private final ProfileRepository repository;
    private final AccountService accountService;

    public Optional<ProfileEntity> getProfile(long id) {
        return this.repository.findById(id);
    }

    public Optional<ProfileEntity> createProfile(ProfileEntity profileEntity, long accountId) {

        ProfileEntity profile = repository.save(profileEntity);
        accountService.updateAccount(profile, accountId);

        return Optional.of(profile);
    }

    public Optional<ProfileEntity> updateProfile(ProfileEntity updateEntity) {
        return Optional.of(repository.save(updateEntity));
    }
}
