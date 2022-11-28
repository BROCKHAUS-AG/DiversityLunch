package de.brockhausag.diversitylunchspringboot.generics.WeightedDimension;

import de.brockhausag.diversitylunchspringboot.generics.Dimension.DimensionEntityService;
import de.brockhausag.diversitylunchspringboot.generics.Dimension.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


public class WeightedModelController<
        DtoType extends WeightedDto,
        EntityType extends WeightedEntity,
        RepositoryType extends CrudRepository<EntityType, Long>,
        ServiceType extends WeightedEntityService<EntityType, RepositoryType>,
        MapperType extends Mapper<DtoType, EntityType>>
        extends DimensionModelController {


    MapperType mapper;
    ServiceType service;

    public WeightedModelController(MapperType mapper, ServiceType service) {
        super(mapper, service);
    }
}

