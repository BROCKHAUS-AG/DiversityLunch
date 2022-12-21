package de.brockhausag.diversitylunchspringboot.generics.weightedDimension;

import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionEntityService;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public class WeightedEntityService<
        EntityType extends WeightedEntity,
        RepositoryType extends CrudRepository<EntityType, Long>> extends DimensionEntityService<
        EntityType, RepositoryType
        > {

    public WeightedEntityService(RepositoryType repository) {
        super(repository);
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
}
