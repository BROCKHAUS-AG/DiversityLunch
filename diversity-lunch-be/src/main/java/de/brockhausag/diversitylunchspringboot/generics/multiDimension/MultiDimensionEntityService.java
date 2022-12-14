package de.brockhausag.diversitylunchspringboot.generics.multiDimension;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntity;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionEntityService;
import org.springframework.data.repository.CrudRepository;

public class MultiDimensionEntityService<
        EntityType extends MultiDimensionEntity,
        RepositoryType extends CrudRepository<EntityType, Long>> extends DimensionEntityService<
        EntityType, RepositoryType> {
    public MultiDimensionEntityService(RepositoryType repository) {
        super(repository);
    }
}
