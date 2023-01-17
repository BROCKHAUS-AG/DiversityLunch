package de.brockhausag.diversitylunchspringboot.generics.weightedDimension;

import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.*;

import java.util.List;
import java.util.Optional;

public class WeightedEntityService<
        EntityType extends WeightedEntity,
        RepositoryType extends WeigthedDimensionRepository<EntityType>>
        extends DimensionEntityService<EntityType, RepositoryType> {

    protected final ProfileService profileService;

    public WeightedEntityService(RepositoryType repository, ProfileService profileService) {
        super(repository);
        this.profileService = profileService;
    }

    public boolean setAsDefault(Long id) {
        var entity = repository.findById(id);
        if(entity.isEmpty()) {
            return false;
        }
        var newDefault = entity.get();
        var oldDefault = getCurrentDefaultEntity().orElseThrow();
        newDefault.setDefault(true);
        oldDefault.setDefault(false);
        repository.saveAll(List.of(newDefault, oldDefault));
        return true;
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
            EntityType targetEntity = repository.findByIsDefaultIsTrue().iterator().next();
            affectedProfiles.forEach((profile) -> {
                if (targetEntity instanceof WorkExperienceEntity) {
                    profile.setWorkExperience((WorkExperienceEntity) targetEntity);
                }
                profile.setWasChangedByAdmin(true);
                profileService.updateProfile(profile);
            });

            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
