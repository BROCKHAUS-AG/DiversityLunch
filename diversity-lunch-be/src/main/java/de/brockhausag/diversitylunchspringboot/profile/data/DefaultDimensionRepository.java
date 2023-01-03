package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DefaultDimensionRepository<EntityType extends DefaultDimensionEntity> extends CrudRepository<EntityType, Long> {
    Iterable<EntityType> findByIsDefaultIsTrue();
}
