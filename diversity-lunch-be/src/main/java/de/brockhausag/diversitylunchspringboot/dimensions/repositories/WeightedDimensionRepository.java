package de.brockhausag.diversitylunchspringboot.dimensions.repositories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimension;
import org.springframework.data.repository.CrudRepository;

public interface WeightedDimensionRepository extends CrudRepository<WeightedDimension, Long> {
}
