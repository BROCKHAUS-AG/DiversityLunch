package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionEntity;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

    private final ProfileRepository repository;
    private final AccountService accountService;

    public Optional<ProfileEntity> getProfile(Long id) {
        return this.repository.findById(id);
    }

    public Iterable<ProfileEntity> getAllProfiles() {
        return this.repository.findAll();
    }

    public Optional<ProfileEntity> createProfile(ProfileEntity profileEntity, Long accountId) {

        ProfileEntity profile = repository.save(profileEntity);
        accountService.updateAccount(profile, accountId);

        return Optional.of(profile);
    }

    public Optional<ProfileEntity> updateProfile(ProfileEntity updateEntity) {
        return Optional.of(repository.save(updateEntity));
    }

   public List<ProfileEntity> getAllProfilesWithSelectedDimensionOption(DimensionEntity dimensionEntity) {
       if (dimensionEntity instanceof CountryEntity) {
           return repository.findAllByOriginCountry((CountryEntity) dimensionEntity);
       } else if (dimensionEntity instanceof DietEntity) {
           return repository.findAllByDiet((DietEntity) dimensionEntity);
       } else if (dimensionEntity instanceof EducationEntity) {
           return repository.findAllByEducation((EducationEntity) dimensionEntity);
       } else if (dimensionEntity instanceof GenderEntity) {
           return repository.findAllByGender((GenderEntity) dimensionEntity);
       } else if (dimensionEntity instanceof HobbyEntity) {
           return repository.findAllByHobby((HobbyEntity) dimensionEntity);
       } else if (dimensionEntity instanceof LanguageEntity) {
           return repository.findAllByMotherTongue((LanguageEntity) dimensionEntity);
       } else if (dimensionEntity instanceof ProjectEntity) {
           return repository.findAllByProject((ProjectEntity) dimensionEntity);
       } else if (dimensionEntity instanceof ReligionEntity) {
           return repository.findAllByReligion((ReligionEntity) dimensionEntity);
       } else if (dimensionEntity instanceof SexualOrientationEntity) {
           return repository.findAllBySexualOrientation((SexualOrientationEntity) dimensionEntity);
       } else if (dimensionEntity instanceof SocialBackgroundDiscriminationEntity) {
           return repository.findAllBySocialBackgroundDiscrimination((SocialBackgroundDiscriminationEntity) dimensionEntity);
       } else if (dimensionEntity instanceof SocialBackgroundEntity) {
           return repository.findAllBySocialBackground((SocialBackgroundEntity) dimensionEntity);
       } else if (dimensionEntity instanceof WorkExperienceEntity) {
           return repository.findAllByWorkExperience((WorkExperienceEntity) dimensionEntity);
       }
       return new ArrayList<>();
   }
}
