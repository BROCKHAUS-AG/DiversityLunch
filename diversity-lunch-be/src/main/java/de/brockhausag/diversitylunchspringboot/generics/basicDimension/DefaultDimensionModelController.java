package de.brockhausag.diversitylunchspringboot.generics.basicDimension;

import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.data.repository.CrudRepository;

public class DefaultDimensionModelController<
        DtoType extends DefaultDimensionDto,
        EntityType extends DefaultDimensionEntity,
        RepositoryType extends CrudRepository<EntityType, Long>,
        ServiceType extends DefaultDimensionEntityService<EntityType, RepositoryType>,
        MapperType extends Mapper<DtoType, EntityType>>
        extends DimensionModelController<
        DtoType, EntityType, RepositoryType, ServiceType, MapperType
        >{
    public DefaultDimensionModelController(MapperType mapper, ServiceType service) {
        super(mapper, service);
    }
}
