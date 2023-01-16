package de.brockhausag.diversitylunchspringboot.generics.defaultDimension;

import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.*;

import java.util.List;
import java.util.Optional;


public class DefaultDimensionEntityService<
        EntityType extends DefaultDimensionEntity,
        RepositoryType extends DefaultDimensionRepository<EntityType>>
        extends DimensionEntityService<EntityType, RepositoryType> {

    protected final ProfileService profileService;

    public DefaultDimensionEntityService(RepositoryType repository, ProfileService profileService) {
        super(repository);
        this.profileService = profileService;
    }

    public boolean setAsDefault(Long id) {
        var entity = repository.findById(id);
        boolean success = false;
        if(entity.isPresent()) {
            var newDefault = entity.get();
            var oldDefault = getCurrentDefaultEntity().orElseThrow();
            newDefault.setDefault(true);
            oldDefault.setDefault(false);
            repository.saveAll(List.of(newDefault, oldDefault));
        }
        return success;
    }

    private Optional<EntityType> getCurrentDefaultEntity() {
        var allEntities = repository.findAll();
        Optional<EntityType> oldDefault = Optional.empty();
        for(var e: allEntities) {
            if(e.isDefault()) {
                oldDefault = Optional.of(e);
            }
        }
        return oldDefault;
    }

    @Override
    public boolean deleteEntityById(Long id) {
        if (repository.existsById(id)) {
            EntityType entity = repository.findById(id).orElseThrow();
            if (entity.isDefault()) {
                return false;
            }
            List<ProfileEntity> affectedProfiles = profileService.getAllProfilesWithSelectedDimensionOption(entity);
            if (!affectedProfiles.isEmpty()) {
                EntityType targetEntity = repository.findByIsDefaultIsTrue().iterator().next();
                affectedProfiles.forEach((profile) -> {
                    if (targetEntity instanceof CountryEntity) {
                        profile.setOriginCountry((CountryEntity) targetEntity);
                    } else if (targetEntity instanceof DietEntity) {
                        profile.setDiet((DietEntity) targetEntity);
                    } else if (targetEntity instanceof EducationEntity) {
                        profile.setEducation((EducationEntity) targetEntity);
                    } else if (targetEntity instanceof GenderEntity) {
                        profile.setGender((GenderEntity) targetEntity);
                    } else if (targetEntity instanceof LanguageEntity) {
                        profile.setMotherTongue((LanguageEntity) targetEntity);
                    } else if (targetEntity instanceof ProjectEntity) {
                        profile.setProject((ProjectEntity) targetEntity);
                    } else if (targetEntity instanceof ReligionEntity) {
                        profile.setReligion((ReligionEntity) targetEntity);
                    } else if (targetEntity instanceof SexualOrientationEntity) {
                        profile.setSexualOrientation((SexualOrientationEntity) targetEntity);
                    } else if (targetEntity instanceof SocialBackgroundDiscriminationEntity) {
                        profile.setSocialBackgroundDiscrimination((SocialBackgroundDiscriminationEntity) targetEntity);
                    } else if (targetEntity instanceof SocialBackgroundEntity) {
                        profile.setSocialBackground((SocialBackgroundEntity) targetEntity);
                    }
                    profile.setWasChangedByAdmin(true);
                    profileService.updateProfile(profile);
                });
            }
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
