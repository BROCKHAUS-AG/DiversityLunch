package de.brockhausag.diversitylunchspringboot.generics.weightedDimension;

import de.brockhausag.diversitylunchspringboot.generics.weightedDimension.WeightedEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface WeigthedDimensionRepository<EntityType extends WeightedEntity> extends CrudRepository<EntityType, Long> {
    Iterable<EntityType> findByIsDefaultIsTrue();
}
