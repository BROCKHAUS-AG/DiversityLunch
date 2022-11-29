package de.brockhausag.diversitylunchspringboot.generics.WeightedDimension;

import de.brockhausag.diversitylunchspringboot.generics.Dimension.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.data.repository.CrudRepository;


public class WeightedModelController<
        DtoType extends WeightedDto,
        EntityType extends WeightedEntity,
        RepositoryType extends CrudRepository<EntityType, Long>,
        ServiceType extends WeightedEntityService<EntityType, RepositoryType>,
        MapperType extends Mapper<DtoType, EntityType>>
        extends DimensionModelController {
    public WeightedModelController(MapperType mapper, ServiceType service) {
        super(mapper, service);
    }
}

