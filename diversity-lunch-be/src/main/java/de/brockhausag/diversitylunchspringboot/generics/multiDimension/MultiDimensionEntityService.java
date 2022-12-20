package de.brockhausag.diversitylunchspringboot.generics.multiDimension;

import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionEntityService;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MultiDimensionEntityService<
        EntityType extends MultiDimensionEntity,
        RepositoryType extends CrudRepository<EntityType, Long>> extends DimensionEntityService<
        EntityType, RepositoryType> {

    public MultiDimensionEntityService(RepositoryType repository) {
        super(repository);
    }

    public List<EntityType> getEntitySelectionByIds(List<Long> idList){
        Iterable<EntityType> entitiesIterable = repository.findAllById(idList);
        return StreamSupport
                .stream(entitiesIterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}
