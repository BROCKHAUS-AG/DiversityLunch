package de.brockhausag.diversitylunchspringboot.generics.dimensionCategory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DimensionCategoryRepository extends CrudRepository<DimensionCategoryEntity, Long> {
    Optional<DimensionCategoryEntity> getDimensionCategoryEntityByDescriptor(String descriptor);
}
