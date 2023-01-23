package de.brockhausag.diversitylunchspringboot.dimensions.repositories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;

import org.springframework.stereotype.Repository;

@Repository
public interface BasicDimensionRepository extends DimensionRepository<BasicDimension, BasicDimensionSelectableOption> {
}
