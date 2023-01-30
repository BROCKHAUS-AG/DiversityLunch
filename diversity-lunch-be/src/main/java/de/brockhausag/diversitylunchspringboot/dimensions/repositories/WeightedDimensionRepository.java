package de.brockhausag.diversitylunchspringboot.dimensions.repositories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;

import org.springframework.stereotype.Repository;

@Repository
public interface WeightedDimensionRepository extends DimensionRepository<WeightedDimension, WeightedDimensionSelectableOption> {
}