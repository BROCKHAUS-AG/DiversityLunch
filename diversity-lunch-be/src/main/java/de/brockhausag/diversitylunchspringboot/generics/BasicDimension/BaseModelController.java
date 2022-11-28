package de.brockhausag.diversitylunchspringboot.generics.BasicDimension;

import de.brockhausag.diversitylunchspringboot.generics.Dimension.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.data.repository.CrudRepository;

public class BaseModelController<
        DtoType extends BaseDto,
        EntityType extends BaseEntity,
        RepositoryType extends CrudRepository<EntityType, Long>,
        ServiceType extends BaseEntityService<EntityType, RepositoryType>,
        MapperType extends Mapper<DtoType, EntityType>>
        extends DimensionModelController {


     MapperType mapper;
     ServiceType service;

    public BaseModelController(MapperType mapper, ServiceType service) {
        super(mapper, service);
    }
}
