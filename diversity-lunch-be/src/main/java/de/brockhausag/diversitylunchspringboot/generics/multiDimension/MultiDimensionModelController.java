package de.brockhausag.diversitylunchspringboot.generics.multiDimension;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionDto;
import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntity;
import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.data.repository.CrudRepository;

public class MultiDimensionModelController <
        DtoType extends MultiDimensionDto,
        EntityType extends MultiDimensionEntity,
        RepositoryType extends CrudRepository<EntityType, Long>,
        ServiceType extends MultiDimensionEntityService<EntityType, RepositoryType>,
        MapperType extends Mapper<DtoType, EntityType>>
        extends DimensionModelController<
        DtoType, EntityType, RepositoryType, ServiceType, MapperType> {
    public MultiDimensionModelController(MapperType mapper, ServiceType service) {
        super(mapper, service);
    }
}
