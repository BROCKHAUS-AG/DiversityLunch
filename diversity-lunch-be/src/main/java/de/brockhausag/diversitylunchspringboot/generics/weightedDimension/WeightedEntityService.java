package de.brockhausag.diversitylunchspringboot.generics.weightedDimension;

import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionEntityService;
import org.springframework.data.repository.CrudRepository;

public class WeightedEntityService <
    EntityType extends WeightedEntity,
    RepositoryType extends CrudRepository<EntityType, Long> > extends DimensionEntityService <
        EntityType, RepositoryType
        > {

    public WeightedEntityService(RepositoryType repository) {
        super(repository);
        }
}
