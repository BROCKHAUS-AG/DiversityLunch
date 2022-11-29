package de.brockhausag.diversitylunchspringboot.generics.BasicDimension;

import de.brockhausag.diversitylunchspringboot.generics.Dimension.DimensionEntityService;
import org.springframework.data.repository.CrudRepository;


public class DefaultDimensionEntityService<
        EntityType extends DefaultDimensionEntity,
        RepositoryType extends CrudRepository<EntityType, Long> > extends DimensionEntityService <
        EntityType, RepositoryType> {

    public DefaultDimensionEntityService(RepositoryType repository) {
        super(repository);
    }

}
