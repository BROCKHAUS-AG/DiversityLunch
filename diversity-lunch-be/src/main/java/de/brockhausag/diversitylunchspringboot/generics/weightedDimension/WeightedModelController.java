package de.brockhausag.diversitylunchspringboot.generics.weightedDimension;

import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionMapper;
import org.springframework.data.repository.CrudRepository;


public class WeightedModelController<
        DtoType extends WeightedDto,
        EntityType extends WeightedEntity,
        RepositoryType extends CrudRepository<EntityType, Long>,
        ServiceType extends WeightedEntityService<EntityType, RepositoryType>,
        MapperType extends DimensionMapper<DtoType, EntityType>>
        extends DimensionModelController<
        DtoType, EntityType, RepositoryType, ServiceType, MapperType
        > {
    public WeightedModelController(MapperType mapper, ServiceType service) {
        super(mapper, service);
    }
}

