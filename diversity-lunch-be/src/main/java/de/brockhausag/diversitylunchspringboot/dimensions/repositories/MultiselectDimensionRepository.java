package de.brockhausag.diversitylunchspringboot.dimensions.repositories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimension;
import org.springframework.data.repository.CrudRepository;

public interface MultiselectDimensionRepository extends CrudRepository<MultiselectDimension, Long> {
}
