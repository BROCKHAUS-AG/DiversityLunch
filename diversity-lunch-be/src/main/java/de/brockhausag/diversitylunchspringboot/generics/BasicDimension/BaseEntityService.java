package de.brockhausag.diversitylunchspringboot.generics.BasicDimension;

import de.brockhausag.diversitylunchspringboot.generics.Dimension.DimensionEntityService;
import org.springframework.data.repository.CrudRepository;


public class BaseEntityService<
        EntityType extends BaseEntity,
        RepositoryType extends CrudRepository<EntityType, Long> > extends DimensionEntityService {

    public BaseEntityService(RepositoryType repository) {
        super(repository);
    }

}
