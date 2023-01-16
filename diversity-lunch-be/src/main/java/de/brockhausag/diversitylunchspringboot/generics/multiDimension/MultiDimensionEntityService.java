package de.brockhausag.diversitylunchspringboot.generics.multiDimension;

import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MultiDimensionEntityService<
        EntityType extends MultiDimensionEntity,
        RepositoryType extends CrudRepository<EntityType, Long>> extends DimensionEntityService<
        EntityType, RepositoryType> {

    protected final ProfileService profileService;

    public MultiDimensionEntityService(RepositoryType repository, ProfileService profileService) {
        super(repository);
        this.profileService = profileService;
    }

    public List<EntityType> getEntitySelectionByIds(List<Long> idList){
        Iterable<EntityType> entitiesIterable = repository.findAllById(idList);
        return StreamSupport
                .stream(entitiesIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteEntityById(Long id) {
        if (repository.existsById(id)) {
            EntityType entity = repository.findById(id).orElseThrow();
            List<ProfileEntity> affectedProfiles = profileService.getAllProfilesWithSelectedDimensionOption(entity);
            if (!affectedProfiles.isEmpty()) {
                affectedProfiles.forEach((profile) -> {
                    List<HobbyEntity> hobbies = profile.getHobby();
                    hobbies.remove(entity);
                    profile.setHobby(hobbies);
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
