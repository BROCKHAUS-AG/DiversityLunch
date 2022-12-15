package de.brockhausag.diversitylunchspringboot.generics.multiDimension;

import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionMapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class MultiDimensionModelController <
        DtoType extends MultiDimensionDto,
        EntityType extends MultiDimensionEntity,
        RepositoryType extends CrudRepository<EntityType, Long>,
        ServiceType extends MultiDimensionEntityService<EntityType, RepositoryType>,
        MapperType extends DimensionMapper<DtoType, EntityType>>
        extends DimensionModelController<
        DtoType, EntityType, RepositoryType, ServiceType, MapperType> {
    public MultiDimensionModelController(MapperType mapper, ServiceType service) {
        super(mapper, service);
    }


    @GetMapping("/selection")
    public ResponseEntity<List<DtoType>> getSelection(@RequestBody List<Long> idList) {
        List<EntityType> entityList = service.getEntitySelectionByIds(idList);
        return new ResponseEntity<>(
                mapper.entityToDto(entityList),
                HttpStatus.OK
        );
    }
}
