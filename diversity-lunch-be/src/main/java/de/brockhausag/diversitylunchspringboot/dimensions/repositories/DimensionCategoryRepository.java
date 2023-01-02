package de.brockhausag.diversitylunchspringboot.dimensions.repositories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DimensionCategoryRepository extends CrudRepository<DimensionCategory, Long> {
    Optional<DimensionCategory> getDimensionCategoryByDescription(String description);
}
