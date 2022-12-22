package de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimensionCategoryRepository extends CrudRepository<DimensionCategory, Long> {
    //Optional<DimensionCategory> getDimensionCategoryEntityByDescriptor(String descriptor);
}
