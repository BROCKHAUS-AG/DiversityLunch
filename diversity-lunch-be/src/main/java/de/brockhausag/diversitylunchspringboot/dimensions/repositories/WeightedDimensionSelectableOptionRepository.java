package de.brockhausag.diversitylunchspringboot.dimensions.repositories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import org.springframework.data.repository.CrudRepository;

public interface WeightedDimensionSelectableOptionRepository extends CrudRepository<WeightedDimensionSelectableOption, Long> {
}
